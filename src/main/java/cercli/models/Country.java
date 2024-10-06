package cercli.models;

import cercli.models.exceptions.UnknownCountryException;

public enum Country {
    USA("United States of America"),
    CANADA("Canada"),
    UK("United Kingdom"),
    AUSTRALIA("Australia"),
    INDIA("India"),
    GERMANY("Germany"),
    FRANCE("France"),
    UAE("United Arab Emirates"),
    JAPAN("Japan"),
    CHINA("China"),
    SOUTH_AFRICA("South Africa"),
    BRAZIL("Brazil"),
    MEXICO("Mexico"),
    RUSSIA("Russia"),
    DUBAI("Dubai");

    private final String countryName;

    Country(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString() {
        return countryName;
    }

    // Method to get Country enum from a country name
    public static Country fromString(String countryName) {
        for (Country country : Country.values()) {
            if (country.getCountryName().equalsIgnoreCase(countryName)) {
                return country;
            }
        }
        try {
            return Country.valueOf(countryName);
        } catch (Exception e) {
            throw new UnknownCountryException("Unknown country: " + countryName);
        }
    }
}
