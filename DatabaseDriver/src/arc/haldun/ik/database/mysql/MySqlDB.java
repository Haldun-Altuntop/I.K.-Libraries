package arc.haldun.ik.database.mysql;

import arc.haldun.ik.applicationform.Application;
import arc.haldun.ik.applicationform.elements.Experience;
import arc.haldun.ik.applicationform.elements.Language;
import arc.haldun.ik.applicationform.elements.Reference;
import arc.haldun.ik.database.IDatabase;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlDB implements IDatabase {

    @Override
    public Application addApplication(Application application) {

        PreparedStatement preparedStatement;
        ResultSet generatedKeys;

        try {

            String sql = "INSERT " +
                    "INTO application " +
                    "(personal_id, name, surname, registration_province, born_place, birth_date, mother_name, " +
                    "father_name, job, ssk_no, email, phone_number, home_phone_number, living_address, " +

                    "military_state, military_entry_date, military_discharge_date, military_duty_area, military_rank, " +

                    "academic_primary_start_date, academic_primary_end_date, academic_primary_name, academic_primary_region, " +

                    "academic_middle_start_date, academic_middle_end_date, academic_middle_name, academic_middle_region, " +

                    "academic_high_start_date, academic_high_end_date, academic_high_name, academic_high_region, " +
                    "academic_high_branch, academic_high_degree, " +

                    "academic_university_start_date, academic_university_end_date, academic_university_name, " +
                    "academic_university_region, academic_university_branch, academic_university_degree, " +

                    "academic_master_start_date, academic_master_end_date, academic_master_name, academic_master_region, " +
                    "academic_master_branch, academic_master_degree, " +

                    "additional_income_amount, additional_currency, additional_residence, additional_social_assurance) " +

                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?)";

            preparedStatement = Connector.connection.prepareStatement(sql);

            // Set personal information
            preparedStatement.setString(1, application.getPersonalInformation().getId());
            preparedStatement.setString(2, application.getPersonalInformation().getName());
            preparedStatement.setString(3, application.getPersonalInformation().getSurname());
            preparedStatement.setString(4, application.getPersonalInformation().getRegistrationProvince());
            preparedStatement.setString(5, application.getPersonalInformation().getBornPlace());
            preparedStatement.setString(6, application.getPersonalInformation().getBirthDate());
            preparedStatement.setString(7, application.getPersonalInformation().getMotherName());
            preparedStatement.setString(8, application.getPersonalInformation().getFatherName());
            preparedStatement.setString(9, application.getPersonalInformation().getJob());
            preparedStatement.setString(10, application.getPersonalInformation().getSskNo());
            preparedStatement.setString(11, application.getPersonalInformation().getEmail());
            preparedStatement.setString(12, application.getPersonalInformation().getPhoneNumber());
            preparedStatement.setString(13, application.getPersonalInformation().getHomePhoneNumber());
            preparedStatement.setString(14, application.getPersonalInformation().getLivingAddress());

            // Set military information
            preparedStatement.setBoolean(15, application.getMilitaryState().isHasDone());
            preparedStatement.setString(16, application.getMilitaryState().getEntryDate());
            preparedStatement.setString(17, application.getMilitaryState().getDischargeDate());
            preparedStatement.setString(18, application.getMilitaryState().getDutyArea());
            preparedStatement.setString(19, application.getMilitaryState().getRank());

            // Set primary school info
            preparedStatement.setString(20, application.getAcademicState().getPrimarySchool().getStartDate());
            preparedStatement.setString(21, application.getAcademicState().getPrimarySchool().getEndDate());
            preparedStatement.setString(22, application.getAcademicState().getPrimarySchool().getName());
            preparedStatement.setString(23, application.getAcademicState().getPrimarySchool().getRegion());

            // Set middle school info
            preparedStatement.setString(24, application.getAcademicState().getMiddleSchool().getStartDate());
            preparedStatement.setString(25, application.getAcademicState().getMiddleSchool().getEndDate());
            preparedStatement.setString(26, application.getAcademicState().getMiddleSchool().getName());
            preparedStatement.setString(27, application.getAcademicState().getMiddleSchool().getRegion());

            // Set high school info
            preparedStatement.setString(28, application.getAcademicState().getHighSchool().getStartDate());
            preparedStatement.setString(29, application.getAcademicState().getHighSchool().getEndDate());
            preparedStatement.setString(30, application.getAcademicState().getHighSchool().getName());
            preparedStatement.setString(31, application.getAcademicState().getHighSchool().getRegion());
            preparedStatement.setString(32, application.getAcademicState().getHighSchool().getBranch());
            preparedStatement.setString(33, application.getAcademicState().getHighSchool().getDegree());

            // Set university info
            preparedStatement.setString(34, application.getAcademicState().getUniversity().getStartDate());
            preparedStatement.setString(35, application.getAcademicState().getUniversity().getEndDate());
            preparedStatement.setString(36, application.getAcademicState().getUniversity().getName());
            preparedStatement.setString(37, application.getAcademicState().getUniversity().getRegion());
            preparedStatement.setString(38, application.getAcademicState().getUniversity().getBranch());
            preparedStatement.setString(39, application.getAcademicState().getUniversity().getDegree());

            // Set master info
            preparedStatement.setString(40, application.getAcademicState().getMaster().getStartDate());
            preparedStatement.setString(41, application.getAcademicState().getMaster().getEndDate());
            preparedStatement.setString(42, application.getAcademicState().getMaster().getName());
            preparedStatement.setString(43, application.getAcademicState().getMaster().getRegion());
            preparedStatement.setString(44, application.getAcademicState().getMaster().getBranch());
            preparedStatement.setString(45, application.getAcademicState().getMaster().getDegree());

            // Set additional information
            preparedStatement.setString(46, application.getAdditionalInfo().getIncomeAmount());
            preparedStatement.setString(47, application.getAdditionalInfo().getCurrency().toString());
            preparedStatement.setString(48, application.getAdditionalInfo().getCurrentResidence().toString());
            preparedStatement.setString(49, application.getAdditionalInfo().getCurrentSocialAssurance().toString());

            preparedStatement.executeUpdate();

            generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                application.setId(generatedKeys.getInt(1));
            }

            // Insert languages
            for (Language language : application.getLanguages()) {
                addLanguage(language);
            }

            // Insert experiences
            for (Experience experience : application.getExperiences()) {
                addExperience(experience);
            }

            // Insert references
            for (Reference reference : application.getReferences()) {
                addReference(reference);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return application;
    }

    @Override
    public Application[] selectApplications() {
        return new Application[0];
    }

    @Override
    public void updateApplication(Application application) {

    }

    @Override
    public void deleteApplication(Application application) {

    }

    @Override
    public Language addLanguage(Language language) {

        try {

            String sql = "INSERT " +
                    "INTO application_list_element_language " +
                    "(parent_application_id,name,speaking_level,reading_writing_level,custom_language_name)" +
                    "VALUES (?,?,?,?,?)";

            PreparedStatement preparedStatement = Connector.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, language.getParentApplicationId());
            preparedStatement.setString(2, language.getName());
            preparedStatement.setString(3, language.getSpeakingLevel().getString().toLowerCase());
            preparedStatement.setString(4, language.getReadingWritingLevel().getString().toLowerCase());
            preparedStatement.setString(5, language.getCustomLanguageName());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                language.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return language;
    }

    @Override
    public Language[] selectLanguages() {

        ArrayList<Language> languages = new ArrayList<>();

        Statement statement;
        ResultSet resultSet;

        try {
            String sql = "SELECT * FROM application_list_element_language";

            statement = Connector.connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                int parentApplicationId = resultSet.getInt("parent_application_id");
                String name = resultSet.getString("name");
                String speakingLevel_str = resultSet.getString("speaking_level");
                String readingWritingLevel_str = resultSet.getString("reading_writing_level");
                String customLanguageName = resultSet.getString("custom_language_name");

                Language.Level speakingLevel = Language.Level.findByValue(speakingLevel_str);
                Language.Level readingWritingLevel = Language.Level.findByValue(readingWritingLevel_str);

                Language language = new Language(
                        id,
                        parentApplicationId,
                        name,
                        speakingLevel,
                        readingWritingLevel,
                        customLanguageName
                );

                languages.add(language);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {

            String msg = "Veritabanına bağlı değilsiniz!";

            Logger.getGlobal().log(
                    Level.WARNING,
                    msg
            );

            JOptionPane.showMessageDialog(
                    null,
                    msg,
                    "Hata!",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return languages.toArray(new Language[0]);
    }

    @Override
    public void updateLanguage(Language language) {

        String sql = "UPDATE application_list_element_language " +
                "SET parent_application_id = ?, " +
                "name = ?, " +
                "speaking_level = ?, " +
                "reading_writing_level = ?, " +
                "custom_language_name = ? " +
                "WHERE id = ?";

        PreparedStatement preparedStatement;

        try {

            preparedStatement = Connector.connection.prepareStatement(sql);
            preparedStatement.setInt(1, language.getParentApplicationId());
            preparedStatement.setString(2, language.getName());
            preparedStatement.setString(3, language.getSpeakingLevel().getString().toLowerCase());
            preparedStatement.setString(4, language.getReadingWritingLevel().getString().toLowerCase());
            preparedStatement.setString(5, language.getCustomLanguageName());
            preparedStatement.setInt(6, language.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                Logger.getGlobal().log(
                        Level.WARNING,
                        "Herhangi bir değişiklik yapılmadı."
                );
            } else {
                Logger.getGlobal().log(
                        Level.INFO,
                        affectedRows + " satır değiştirildi."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     *
     * @param language Silinecek satırın id değerini taşımalı. Eğer id = 0 ise tüm tabloyu siler.
     */
    @Override
    public void deleteLanguage(Language language) {

        PreparedStatement preparedStatement;

        try {
            String sql =
                    "DELETE " +
                    "FROM application_list_element_language";

            if (language.getId() != 0) {
                sql += " WHERE id = ?";

                preparedStatement = Connector.connection.prepareStatement(sql);
                preparedStatement.setInt(1, language.getId());
            } else {
                preparedStatement = Connector.connection.prepareStatement(sql);
            }

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                Logger.getGlobal().log(
                        Level.WARNING,
                        "Herhangi bir değişiklik yapılmadı."
                );
            } else {
                Logger.getGlobal().log(
                        Level.INFO,
                        affectedRows + " satır değiştirildi."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Experience addExperience(Experience experience) {

        PreparedStatement preparedStatement;
        ResultSet generatedKeys;

        try {

            String sql = "INSERT " +
                    "INTO application_list_element_experience " +
                    "(parent_application_id, company, start_date, quit_date, reference, position, salary, cause_of_quit) " +
                    "VALUES (?,?,?,?,?,?,?,?)";

            preparedStatement = Connector.connection.prepareStatement(sql);
            preparedStatement.setInt(1, experience.getParentApplicationId());
            preparedStatement.setString(2, experience.getCompany());
            preparedStatement.setString(3, experience.getStartingDate());
            preparedStatement.setString(4, experience.getQuittingDate());
            preparedStatement.setString(5, experience.getReference());
            preparedStatement.setString(6, experience.getPosition());
            preparedStatement.setString(7, experience.getSalary());
            preparedStatement.setString(8, experience.getCauseOfQuit());

            preparedStatement.executeUpdate();

            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                experience.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return experience;
    }

    @Override
    public Experience[] selectExperiences() {

        ArrayList<Experience> experiences = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;

        try {
            String sql = "SELECT * " +
                    "FROM application_list_element_experience";

            statement = Connector.connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                int parentApplicationId = resultSet.getInt("parent_application_id");
                String company = resultSet.getString("company");
                String startingDate = resultSet.getString("start_date");
                String quittingDate = resultSet.getString("quit_date");
                String reference = resultSet.getString("reference");
                String position = resultSet.getString("position");
                String salary = resultSet.getString("salary");
                String causeOfQuit = resultSet.getString("cause_of_quit");

                Experience experience = new Experience(
                        id,
                        parentApplicationId,
                        company,
                        startingDate,
                        quittingDate,
                        reference,
                        position,
                        salary,
                        causeOfQuit
                );

                experiences.add(experience);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return experiences.toArray(new Experience[0]);
    }

    @Override
    public void updateExperience(Experience experience) {

        PreparedStatement preparedStatement;

        try {

            String sql = "UPDATE application_list_element_experience " +
                    "SET parent_application_id = ?, " +
                    "company = ?, " +
                    "start_date = ?, " +
                    "quit_date = ?, " +
                    "reference = ?, " +
                    "position = ?, " +
                    "salary = ?, " +
                    "cause_of_quit = ? " +
                    "WHERE id = ?";

            preparedStatement = Connector.connection.prepareStatement(sql);
            preparedStatement.setInt(1, experience.getParentApplicationId());
            preparedStatement.setString(2, experience.getCompany());
            preparedStatement.setString(3, experience.getStartingDate());
            preparedStatement.setString(4, experience.getQuittingDate());
            preparedStatement.setString(5, experience.getReference());
            preparedStatement.setString(6, experience.getPosition());
            preparedStatement.setString(7, experience.getSalary());
            preparedStatement.setString(8, experience.getCauseOfQuit());
            preparedStatement.setInt(9, experience.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                Logger.getGlobal().log(
                        Level.WARNING,
                        "Herhangi bir değişiklik yapılmadı."
                );
            } else {
                Logger.getGlobal().log(
                        Level.INFO,
                        affectedRows + " satır değiştirildi."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteExperience(Experience experience) {

        Statement statement;

        try {

            String sql = "DELETE " +
                    "FROM application_list_element_experience ";

            if (experience.getId() != 0) {
                sql += "WHERE id = " + experience.getId();
            }

            statement = Connector.connection.createStatement();

            int affectedRows = statement.executeUpdate(sql);

            if (affectedRows == 0) {
                Logger.getGlobal().log(
                        Level.WARNING,
                        "Herhangi bir değişiklik yapılmadı."
                );
            } else {
                Logger.getGlobal().log(
                        Level.INFO,
                        affectedRows + " satır değiştirildi."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Reference addReference(Reference reference) {

        PreparedStatement preparedStatement;
        ResultSet generatedKeys;

        try {
            String sql = "INSERT " +
                    "INTO application_list_element_reference " +
                    "(parent_application_id, name_surname, job, company, phone, relativity_degree) " +
                    "VALUES (?,?,?,?,?,?)";

            preparedStatement = Connector.connection.prepareStatement(sql);
            preparedStatement.setInt(1, reference.getParentApplicationId());
            preparedStatement.setString(2, reference.getNameSurname());
            preparedStatement.setString(3, reference.getJob());
            preparedStatement.setString(4, reference.getCompany());
            preparedStatement.setString(5, reference.getPhone());
            preparedStatement.setString(6, reference.getRelativityDegree());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                Logger.getGlobal().log(
                        Level.WARNING,
                        "Herhangi bir değişiklik yapılmadı."
                );
            } else {
                Logger.getGlobal().log(
                        Level.INFO,
                        affectedRows + " satır değiştirildi."
                );
            }

            generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                reference.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reference;
    }

    @Override
    public Reference[] selectReferences() {

        ArrayList<Reference> references = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;

        try {

            String sql = "SELECT * " +
                    "FROM application_list_element_reference";

            statement = Connector.connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                int parentApplicationId = resultSet.getInt("parent_application_id");
                String nameSurname = resultSet.getString("name_surname");
                String job = resultSet.getString("job");
                String company = resultSet.getString("company");
                String phone = resultSet.getString("phone");
                String relativityDegree = resultSet.getString("relativity_degree");

                Reference reference = new Reference(
                        id,
                        parentApplicationId,
                        nameSurname,
                        job,
                        company,
                        phone,
                        relativityDegree
                );

                references.add(reference);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return references.toArray(new Reference[0]);
    }

    @Override
    public void updateReference(Reference reference) {

        PreparedStatement preparedStatement;

        try {
            String sql = "UPDATE application_list_element_reference " +
                    "SET parent_application_id = ?," +
                    "name_surname = ?," +
                    "job = ?," +
                    "company = ?," +
                    "phone = ?," +
                    "relativity_degree = ? " +
                    "WHERE id = ?";

            preparedStatement = Connector.connection.prepareStatement(sql);
            preparedStatement.setInt(1, reference.getParentApplicationId());
            preparedStatement.setString(2, reference.getNameSurname());
            preparedStatement.setString(3, reference.getJob());
            preparedStatement.setString(4, reference.getCompany());
            preparedStatement.setString(5, reference.getPhone());
            preparedStatement.setString(6, reference.getRelativityDegree());
            preparedStatement.setInt(7, reference.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                Logger.getGlobal().log(
                        Level.WARNING,
                        "Herhangi bir değişiklik yapılmadı."
                );
            } else {
                Logger.getGlobal().log(
                        Level.INFO,
                        affectedRows + " satır değiştirildi."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteReference(Reference reference) {

        Statement statement;
        String sql = "DELETE " +
                "FROM application_list_element_reference ";

        if (reference.getId() != 0) {
            sql += "WHERE id = " + reference.getId();
        }

        try {

            statement = Connector.connection.createStatement();

            int affectedRows = statement.executeUpdate(sql);

            if (affectedRows == 0) {
                Logger.getGlobal().log(
                        Level.WARNING,
                        "Herhangi bir değişiklik yapılmadı."
                );
            } else {
                Logger.getGlobal().log(
                        Level.INFO,
                        affectedRows + " satır değiştirildi."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
