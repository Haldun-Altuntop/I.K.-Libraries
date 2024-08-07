package arc.haldun.ik.database;

import arc.haldun.ik.applicationform.Application;
import arc.haldun.ik.applicationform.elements.Experience;
import arc.haldun.ik.applicationform.elements.Language;
import arc.haldun.ik.applicationform.elements.Reference;

public class DatabaseManager {

    private IDatabase database;

    public DatabaseManager(IDatabase database) {
        this.database = database;
    }

    //
    // Application
    //

    /**
     * Adds the specified Application and returns the same with id.
     * @param application Application to insert
     * @return The same Application with id.
     */
    public Application addApplication(Application application) {
        return database.addApplication(application);
    }

    public Application[] selectApplications() {
        return database.selectApplications();
    }

    public void updateApplication(Application application) {
        database.updateApplication(application);
    }

    public void deleteApplication(Application application) {
        database.deleteApplication(application);
    }

    //
    // Language
    //

    public Language addLanguage(Language language) {
        return database.addLanguage(language);
    }

    public Language[] selectLanguages() {
        return database.selectLanguages();
    }

    public void updateLanguage(Language language) {
        database.updateLanguage(language);
    }

    public void deleteLanguage(Language language) {
        database.deleteLanguage(language);
    }

    //
    // Experience
    //

    public Experience addExperience(Experience experience) {
        return database.addExperience(experience);
    }

    public Experience[] selectExperiences() {
        return database.selectExperiences();
    }

    public void updateExperience(Experience experience) {
        database.updateExperience(experience);
    }

    public void deleteExperience(Experience experience) {
        database.deleteExperience(experience);
    }

    //
    // Reference
    //

    public Reference addReference(Reference reference) {
        return database.addReference(reference);
    }

    public Reference[] selectReferences() {
        return database.selectReferences();
    }

    public void updateReference(Reference reference) {
        database.updateReference(reference);
    }

    public void deleteReference(Reference reference) {
        database.deleteReference(reference);
    }
}
