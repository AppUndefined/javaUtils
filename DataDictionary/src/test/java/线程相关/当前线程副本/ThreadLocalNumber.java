package 线程相关.当前线程副本;

public class ThreadLocalNumber {
    private static ThreadLocal<Integer> num = new ThreadLocal<Integer>(){
        public Integer initialValue() {
            return 0;
        }
    };
    public Integer getNextNum(){
        num.set(num.get()+1);
        return num.get()-1;
    }
    public void remove(){
        num.remove();
    }
}
