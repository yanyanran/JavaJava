public class Execute {
    public void execute(Runnable command) extends ThreadPoolExecutor {
        if (command == null) {
            throw new NullPointerException();
        } else {
            int c = this.ctl.get();
            if (workerCountOf(c) < this.corePoolSize) {
                if (this.addWorker(command, true)) {
                    return;
                }

                c = this.ctl.get();
            }

            if (isRunning(c) && this.workQueue.offer(command)) {
                int recheck = this.ctl.get();
                if (!isRunning(recheck) && this.remove(command)) {
                    this.reject(command);
                } else if (workerCountOf(recheck) == 0) {
                    this.addWorker((Runnable)null, false);
                }
            } else if (!this.addWorker(command, false)) {
                this.reject(command);
            }

        }
    }
}