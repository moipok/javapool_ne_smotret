public class UserIdsGenerator {
    private static UserIdsGenerator instance;
    private static Integer count = 0;
    private UserIdsGenerator() {}
    public static UserIdsGenerator getInstance(){
        if(instance == null){
            instance = new UserIdsGenerator();
        }
        return instance;
    }
    public static int generateId(){
        return ++count;
    }
}
