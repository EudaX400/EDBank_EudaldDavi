package application;

/**
 * 
 * @author Eudald Daví and Dídac Martínez Represents a user in the system.
 *
 */
public class User {
	private int idUser;
	private String userName;
	private String titular_name;
	private String password;
	private int age;
	private int phoneNumber;
	private String entityBankName;
	private String mail;

	/**
	 * Constructs a new User object with the specified details.
	 *
	 * @param idUser          : The user ID.
	 * @param userName        : The user name.
	 * @param titular_name    : The titular name.
	 * @param password        : The user's password.
	 * @param age             : The user's age.
	 * @param phoneNumber     : The user's phone number.
	 * @param entityBankName: The name of the user's bank entity.
	 * @param mail            : The user's email address.
	 */
	public User(int idUser, String userName, String titular_name, String password, int age, int phoneNumber,
			String entityBankName, String mail) {
		this.idUser = idUser;
		this.userName = userName;
		this.titular_name = titular_name;
		this.password = password;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.entityBankName = entityBankName;
		this.mail = mail;
	}

	/**
	 * Retrieves the user ID.
	 *
	 * @return The user ID.
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * Retrieves the user name.
	 *
	 * @return The user name.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName : The user name to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Retrieves the titular name.
	 *
	 * @return The titular name.
	 */
	public String getTitular_name() {
		return titular_name;
	}

	/**
	 * Retrieves the user's password.
	 *
	 * @return The user's password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user's password.
	 *
	 * @param password : The user's password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Retrieves the user's age.
	 *
	 * @return The user's age.
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the user's age.
	 *
	 * @param age : The user's age to set.
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Retrieves the user's phone number.
	 *
	 * @return The user's phone number.
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the user's phone number.
	 *
	 * @param phoneNumber : The user's phone number to set.
	 */
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Retrieves the name of the user's bank entity.
	 *
	 * @return The name of the user's bank entity.
	 */
	public String getEntityBankName() {
		return entityBankName;
	}

	/**
	 * Sets the name of the user's bank entity.
	 *
	 * @param entityBankName : The name of the user's bank entity to set.
	 */
	public void setEntityBankName(String entityBankName) {
		this.entityBankName = entityBankName;
	}

	/**
	 * Retrieves the user's email address.
	 *
	 * @return The user's email address.
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the user's email address.
	 *
	 * @param mail : The user's email address to set.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

}
