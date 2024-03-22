package testdata;

import com.github.javafaker.Faker;

import java.util.Locale;

import static utils.RandomUtils.getRandomInt;

public class testData {

    public Faker faker = new Faker(new Locale("eng"));

    public String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = getRandomEmail(),
            userAddress = getRandomAddress(),
            userPhoneNumber = getRandomPhone(),
            userGender = getRandomGender(),
            usersMonthOfBirth = getRandomMonth(),
            usersHobbie = getRandomHobbie(),
            usersPicture = "apple-touch-icon.png",
            usersSubject = getRandomSubject(),
            usersState = getRandomState(),
            usersCity = getRandomCity(usersState);


    public Integer usersDayOfBirth = getRandomDay(),
    usersYearOfBirth = getRandomYear();


    public String getRandomEmail() {
        return faker.internet().emailAddress();

    }

    public String getRandomAddress() {
        return faker.address().streetAddress();

    }

    public String getRandomPhone() {
        return String.format("%s%s", getRandomInt(1, 9), getRandomInt(100000000, 999999999));

    }

    public String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};

        int index = getRandomInt(0, genders.length - 1);

        return genders[index];

    }

    public int getRandomDay() {
        return getRandomInt(1, 28);

    }

    public String getRandomMonth() {
        return faker.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December");

    }

    public int getRandomYear() {
        return getRandomInt(1900, 2010);

    }

    public String getRandomHobbie() {
        return faker.options().option("Sports", "Reading", "Music");

    }

    public String getRandomSubject() {
        return faker.options().option("English", "Arts", "History", "Chemistry", "Math", "Hindi");

    }

    public String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");

    }

    public String getRandomCity(String usersState) {
        switch (usersState) {
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana":
                return faker.options().option("Karnal", "Panipat");
            case "Rajasthan":
                return faker.options().option("Jaipur", "Jaiselmer");
            default:
                return null;
        }
    }


}
