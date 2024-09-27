package edu.ucalgary.oop;

interface LogInquirerQueries {
    /**
     * Logs an inquirer query into the database.
     *
     * @param id                The ID of the inquirer.
     * @param firstName         The first name of the inquirer.
     * @param lastName          The last name of the inquirer.
     * @param servicesPhoneNum  The services phone number of the inquirer.
     */
    void logInquirerQuery(int id, String firstName, String lastName, String servicesPhoneNum);

    /**
     * Searches for an inquirer in the database based on the provided part of the name.
     *
     * @param partOfName The part of the name to search for.
     * @return A string containing the matching inquirers.
     */
    String searchInquirer(String partOfName);

    /**
     * Checks if the inquirer is a central relief worker.
     *
     * @return True if the inquirer is a central relief worker, false otherwise.
     */
    boolean isCentralReliefWorker();

    /**
     * Sets the central relief worker status for the inquirer.
     *
     * @param isCentral True to set the inquirer as a central relief worker, false otherwise.
     */
    void setCentralReliefWorker(boolean isCentral);
}