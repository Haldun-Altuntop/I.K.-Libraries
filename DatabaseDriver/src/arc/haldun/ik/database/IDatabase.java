package arc.haldun.ik.database;

import arc.haldun.ik.applicationform.Application;
import arc.haldun.ik.applicationform.elements.Experience;
import arc.haldun.ik.applicationform.elements.Language;
import arc.haldun.ik.applicationform.elements.Reference;

public interface IDatabase {

    /**
     * Creates an application and inserts to database then returns application with id.
     * @param application Created application
     * @return The same application with id.
     */
    Application addApplication(Application application);

    Application[] selectApplications();
    void updateApplication(Application application);
    void deleteApplication(Application application);

    /**
     * Creates a language and inserts to database then returns language with id.
     * @param language Created language
     * @return The same language with id.
     */
    Language addLanguage(Language language);

    Language[] selectLanguages();
    void updateLanguage(Language language);
    void deleteLanguage(Language language);

    /**
     * Creates an experience and inserts to database then returns experience with id.
     * @param experience Created experience
     * @return The same experience with id.
     */
    Experience addExperience(Experience experience);

    Experience[] selectExperiences();
    void updateExperience(Experience experience);
    void deleteExperience(Experience experience);

    /**
     * Creates a reference and inserts to database then returns reference with id.
     * @param reference Created reference
     * @return The same reference with id.
     */
    Reference addReference(Reference reference);

    Reference[] selectReferences();
    void updateReference(Reference reference);
    void deleteReference(Reference reference);
}
