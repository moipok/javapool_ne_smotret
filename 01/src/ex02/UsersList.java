public interface UsersList {
    void addUser(User user);
    User getUserByID(int id) throws UserNotFoundException;
    User getUserByIndex(int index) throws UserNotFoundException;
    Integer NumberOfUsers();
}
