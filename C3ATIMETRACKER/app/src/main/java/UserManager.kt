object UserManager {

    private val userList: MutableList<Users> = mutableListOf()


    fun loginUser(username: String, password: String): Boolean {
        for (user in userList) {
            if (user.username == username && user.password == password) {
                return true
            }
        }
        return false
    }
    fun addUser(user: Users) {
        userList.add(user)
    }



}
