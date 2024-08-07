package arc.haldun.ik.database;

import arc.haldun.ik.applicationform.Application;
import arc.haldun.ik.applicationform.elements.*;
import arc.haldun.ik.applicationform.info.AdditionalInfo;
import arc.haldun.ik.applicationform.info.MilitaryState;
import arc.haldun.ik.applicationform.info.PersonalInformation;
import arc.haldun.ik.applicationform.info.academicstate.AcademicState;
import arc.haldun.ik.applicationform.info.academicstate.First;
import arc.haldun.ik.applicationform.info.academicstate.High;
import arc.haldun.ik.database.mysql.Connector;
import arc.haldun.ik.database.mysql.MySqlDB;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static DatabaseManager mySqlDbManager = new DatabaseManager(new MySqlDB());

    public static void main(String[] args) throws InterruptedException, IOException {

        //createConfig();
        //testConnection();

        connect();

        //addApplication();

        Thread.sleep(1000);
        disconnect();
    }

    static void addApplication() throws InterruptedException {

        Application application = new Application();

        application.setPersonalInformation(new PersonalInformation(
                "11594318440", "Haldun", "Altuntop", "Baliseyh", "Iskenderun", "2007",
                "Neval", "Tayfun", "Yazılım Mühendisi", "999", "altuntophaldun@gmail.com", "+90 542 112 55 48",
                "", "A", "+", 0, 0, new DrivingLicence(), "MYVISTA Sitesi"
        ));

        application.setMilitaryState(new MilitaryState(false));

        application.setAcademicState(new AcademicState(
                new First("2011", "2015", "Sebati Günenç İlkoklu", "Hatay/Belen"),
                new First("2016", "2020", "Sebati Günenç Ortaokulu", "Hatay/Belen"),
                new High("2021", "-", "İskenderun Cumhuryet Anadolu Lisesi", "İskenderun", "-", "-"),
                High.EMPTY,
                High.EMPTY
        ));

        application.setLanguages(new Language[]{
                new Language("İngilizce", Language.Level.INTERMEDIATE, Language.Level.INTERMEDIATE),
                new Language("Rusça", Language.Level.BEGINNER, Language.Level.BEGINNER)
        });

        application.setAdditionalInfo(new AdditionalInfo(
                "150000",
                Currency.DOLLAR,
                Residence.POSSESSION,
                SocialAssurance.SSK
        ));

        application.setExperiences(new Experience[]{
                new Experience("Haldun Ltd. Şti.", "2020", "2030", "Haldun Altuntop", "Yazılım Mühendisi", "200000", "Can sıkıntısı")
        });

        application.setReferences(new Reference[]{
                new Reference("Haldun Altuntop", "Müdür", "Haldun Entertainment Studios", "+90 555 555 55 55", "Bizzat kendisi")
        });

        System.out.println("Başvuru ekleniyor...");
        mySqlDbManager.addApplication(application);
        System.out.println("Başvuru eklendi!");
    }

    static void deleteReference() {

        Reference reference = new Reference();
        reference.setId(0);

        System.out.println("Referans siliniyor...");
        mySqlDbManager.deleteReference(reference);
        System.out.println("Referans silindi!");

    }

    static void updaterReference() {

        Reference reference = new Reference(
                2,
                0,
                "Haldun Altuntop",
                "Dahiliye Cerrahı",
                "Haldun Oyun Geliştirmeme Şirketi",
                "+90 542 112 55 78",
                "Zaatın kendisi"
        );

        System.out.println("Referans güncelleniyor...");
        mySqlDbManager.updateReference(reference);
        System.out.println("Referans güncellendi!");

    }

    static void selectReferences() {

        Reference[] references = mySqlDbManager.selectReferences();

        for (Reference reference : references) {

            System.out.println("\n");
            System.out.println("Id: " + reference.getId());
            System.out.println("Parent application id: " + reference.getParentApplicationId());
            System.out.println("Name/Surname: " + reference.getNameSurname());
            System.out.println("Job: " + reference.getJob());
            System.out.println("Company: " + reference.getCompany());
            System.out.println("Phone: " + reference.getPhone());
            System.out.println("Relativity degree: " + reference.getRelativityDegree());

        }

    }

    static void addReference() {

        Reference reference = new Reference(
                "Bahadır Altuntop",
                "Dahiliye doktoru",
                "Haldun Oyun Geliştirme Şirketi",
                "+90 542 112 55 78",
                "Bizzat kendisi"
        );

        System.out.println("Referans ekleniyor...");
        mySqlDbManager.addReference(reference);
        System.out.println("Referans eklendi!");

        System.out.println("Referansın veritabanı numarası: " + reference.getId());

    }

    static void updateExperience() {

        Experience experience = Experience.emptyInstance();
        experience.setId(18);
        experience.setParentApplicationId(0);
        experience.setCompany("Haldun Studios");
        experience.setStartingDate("2010");
        experience.setQuittingDate("2020");
        experience.setReference("Haldun Altuntop");
        experience.setPosition(" Sadece Yazılım Mühendisi");
        experience.setSalary("200000");
        experience.setCauseOfQuit("Fantezi");

        System.out.println("Deneyim güncelleniyor...");
        mySqlDbManager.updateExperience(experience);
        System.out.println("Deneyim güncellendi!");

    }

    static void selectExperiences() {

        Experience[] experiences = mySqlDbManager.selectExperiences();

        for (Experience experience : experiences) {
            System.out.println("\n");
            System.out.println("Id: " + experience.getId());
            System.out.println("Parent application id: " + experience.getParentApplicationId());
            System.out.println("Company: " + experience.getCompany());
            System.out.println("Starting date: " + experience.getStartingDate());
            System.out.println("Quitting date: " + experience.getQuittingDate());
            System.out.println("Reference: " + experience.getReference());
            System.out.println("Position: " + experience.getPosition());
            System.out.println("Salary: " + experience.getSalary());
            System.out.println("Cause of quit: " + experience.getCauseOfQuit());
        }

    }

    static void deleteExperience() {

        Experience experience = Experience.emptyInstance();

        System.out.println("Deneyim siliniyor...");
        mySqlDbManager.deleteExperience(experience);
        System.out.println("Deneyim silindi.");

    }

    static void addExperience() {

        Experience experience = Experience.emptyInstance();
        experience.setParentApplicationId(0);
        experience.setCompany("Haldun Studios");
        experience.setStartingDate("2010");
        experience.setQuittingDate("2020");
        experience.setReference("Fatma Nur Altuntop");
        experience.setPosition(" Sadece Yazılım Mühendisi");
        experience.setSalary("200000");
        experience.setCauseOfQuit("Fantezi");

        System.out.println("Deneyim ekleniyor...");

        mySqlDbManager.addExperience(experience);
        System.out.println("Deneyim eklendi!");

        System.out.println("Deneyimin veritabanı numarası: " + experience.getId());

    }

    static void deleteLanguage() {

        Language language = new Language();
        //language.setId(0);

        mySqlDbManager.deleteLanguage(language);
    }

    static void updateLanguage() {

        Language language = new Language();
        language.setId(0);
        language.setParentApplicationId(1);
        language.setName("");
        language.setSpeakingLevel(Language.Level.ADVANCED);
        language.setReadingWritingLevel(Language.Level.INTERMEDIATE);
        language.setCustomLanguageName("İbranice");

        mySqlDbManager.updateLanguage(language);
    }

    static void selectLanguages() {

        Language[] languages = mySqlDbManager.selectLanguages();

        for (Language language : languages) {

            System.out.println("\n");
            System.out.println("Id: " + language.getId());
            System.out.println("Parent application id: " + language.getParentApplicationId());
            System.out.println("Name: " + language.getName());
            System.out.println("Speaking level: " + language.getSpeakingLevel());
            System.out.println("Reading/Writing level: " + language.getReadingWritingLevel());
            System.out.println("Custom language name: " + language.getCustomLanguageName());

        }
    }

    static void addLanguage() {
        System.out.println("dil ekleniyor...");
        Language language = new Language();
        language.setParentApplicationId(0);
        language.setName("");
        language.setSpeakingLevel(Language.Level.BEGINNER);
        language.setReadingWritingLevel(Language.Level.BEGINNER);
        language.setCustomLanguageName("Farsça");

        language = mySqlDbManager.addLanguage(language);
        System.out.println("Dil Eklendi");

        System.out.println("Dilin veritabanı numarası: " + language.getId());
    }

    static void connect() {

        System.out.println("Bağlanıyor...");

        Connector.connect();

        System.out.print("Bağlantı durumu: ");

        if (Connector.isConnected()) {
            System.out.println("bağlı");
        } else {
            System.out.println("bağlanmadı");
        }
    }

    static void disconnect() {

        Logger.getGlobal().log(
                Level.INFO,
                "Bağlantı kapatılıyor..."
        );

        Connector.disconnect();
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Logger.getGlobal().log(
                Level.INFO,
                "Bağlantı kapatıldı."
        );
    }

    static void testConnection() throws InterruptedException {

        connect();
        Thread.sleep(500);

        disconnect();
    }

    static void createConfig() {
        DatabaseConfig databaseConfig = new DatabaseConfig(
                "cryogold.com.tr",
                "cryogold_com_tr_elib",
                "haldun",
                "E-LibDatabaseUserHaldun"
        );

        databaseConfig.save("test-config");
    }
}