# message_app_hibernate_and_encrypted_messages
A basic messaging application that uses Hibernate to manage a MySQL database and implements public keys to encrypt messages before sending them and decrypt them when receiving them.
To run the aplication, an empty database must be created for hibernate to build and manage it. The name of the database is visible on the persitence.xml file, found in the resources folder. In that same file, a user and password with enought permits must be set, so hibernate is able to act throught it.

Lastly, class Main must be executed.
