import com.mysql.cj.xdevapi.Collection;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.DbDocImpl;
import com.mysql.cj.xdevapi.DocResult;
import com.mysql.cj.xdevapi.JsonNumber;
import com.mysql.cj.xdevapi.JsonString;
import com.mysql.cj.xdevapi.Schema;
import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.SessionFactory;

/*
 * 示例程序展示了如何使用 Connector/J 的 Dev API 支持
 */
public class DevApiSample {
    public static void main(String[] args) {
        Session session = new SessionFactory().getSession("mysqlx://localhost:33060/test?user=user&password=password1234");
        System.err.println("Connected!");
        Schema schema = session.getDefaultSchema();
        System.err.println("Default schema is: " + schema);

        documentWalkthrough(schema);
    }

    public static void documentWalkthrough(Schema schema) {
        // 文件演练
        Collection coll = schema.createCollection("myBooks", /* reuseExisting? */ true);
        DbDoc newDoc = new DbDocImpl().add("isbn", new JsonString().setValue("12345"));
        newDoc.add("title", new JsonString().setValue("Effi Briest"));
        newDoc.add("author", new JsonString().setValue("Theodor Fontane"));
        newDoc.add("currentlyReadingPage", new JsonNumber().setValue(String.valueOf(42)));
        coll.add(newDoc).execute();

        // 注意：文档路径的“$”前缀是可选的。 "$.title.somethingElse[0]" 与文档表达式中的 "title.somethingElse[0]" 相同
        DocResult docs = coll.find("$.title = 'Effi Briest' and $.currentlyReadingPage > 10").execute();
        DbDoc book = docs.next();
        System.err.println("Currently reading " + ((JsonString) book.get("title")).getString() + " on page "
                + ((JsonNumber) book.get("currentlyReadingPage")).getInteger());

        // 增加页码并再次获取它
        coll.modify("$.isbn = 12345").set("$.currentlyReadingPage", ((JsonNumber) book.get("currentlyReadingPage")).getInteger() + 1).execute();

        docs = coll.find("$.title = 'Effi Briest' and $.currentlyReadingPage > 10").execute();
        book = docs.next();
        System.err.println("Currently reading " + ((JsonString) book.get("title")).getString() + " on page "
                + ((JsonNumber) book.get("currentlyReadingPage")).getInteger());

        // 删除文档
        coll.remove("true").execute();
        System.err.println("Number of books in collection: " + coll.count());

        schema.dropCollection(coll.getName());
    }
}
