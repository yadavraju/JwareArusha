package com.jwareconsulting.arusha.common;


import com.jwareconsulting.arusha.R;

/**
 * Created by anjulkhanal on 9/8/16.
 */
public class IconManager {


    private static final String TAG = "IconManager";
    public static final String BANKS = "Banks";
    public static final String AIRPORT = "Airports and Airlines";
    public static final String ACCOUNTANTS_AUDITORS = "Accountants & Auditors";
    public static final String ATM = "ATMs";
    public static final String BARS_CLUBS = "Bars & Clubs";
    public static final String BUREAU_DE_CHANGE = "Bureau de Change";
    public static final String BUSINESS_CONSULTING = "Business Consulting";
    public static final String BUTCHER = "Butche";
    public static final String CAR_RENTALS = "Car Rentals";
    public static final String CINEMAS = "Cinemas";
    public static final String CLOTHING_APPAREL = "Clothing & Apparel";
    public static final String COMMERICAL_WARES = "Commercial Wares";
    public static final String AUTO = "Auto";
    public static final String BARS_CLUBS1 = "Bars & Clubs";
    public static final String PRINTERS = "Printers";
    public static final String EDUCATION = "Education";
    public static final String FUEL_STATIONS = "Fuel Stations";
    public static final String FURNITURE = "Furniture";
    public static final String GOVERNMENT_OFFICES = "Government Offices";
    public static final String HOSPITALS_AND_CLINICS = "Hospitals and Clinics";
    public static final String RESTURANTS = "Restaurants";
    public static final String HOTELS_LODGING = "Hotels & Lodging";
    public static final String HOUSEWARES = "Housewares";
    public static final String INSURANCE = "Insurance";
    public static final String INTERIOR_DESIGN = "Interior Design";
    public static final String LEGAL_ADVOCATES = "Legal Advocates";
    public static final String PHARMACIES = "Pharma";
    public static final String POINTS_OF_INTEREST = "Points of Interest";
    public static final String POST_OFFICES = "Post Offices";
    public static final String REAL_ESTATE = "Real Estate";
    public static final String RECREATION = "Recreation";
    public static final String SECURITY = "Security";
    public static final String SHOPPING_CENTERS = "Shopping Centers";
    public static final String SPA_WELLNESS = "Spa & Wellness";
    public static final String SOFTWARE_INTERNET_PROVIDERS = "Software & Internet Providers";
    public static final String SUPERMARKETS_GROCERY = "Supermarkets / Grocery";
    public static final String TAXI_SHUTTLES = "Taxi & Shuttles";
    public static final String THEATER_ARTS = "Theater & Arts";
    public static final String SAFARI_TOURISM = "Safari & Tourism";
    public static final String TRAVEL_AGENCY = "Travel Agency";
    public static final String ORGANISATION_NGO = "Organizations & NGO";
    public static final String MISCELLANEOUS = "Miscellaneous";
    public static final String GIFT_SHOP = "Gift Shop";
    public static final String ANIMAL_CARE = "Animal Care";
    public static final String ELECTRONICS = "Electronics";
    public static final String JEWELRY = "Jewelr";

    public static int getResources(String title) {

        if (ValidationUtils.isEmpty(title)) {
            return R.drawable.ic_marker_current_location;
        }

        if (title.startsWith(BANKS)) {
            return R.drawable.ic_marker_bank;
        } else if (title.startsWith(ORGANISATION_NGO)) {
            return R.drawable.ic_marker_organizations__ngos;
        }
        else if (title.startsWith(JEWELRY)) {
            return R.drawable.ic_marker_jewlery;
        }



        else if (title.startsWith(ELECTRONICS)) {
            return R.drawable.ic_marker_electronics;
        } else if (title.startsWith(AIRPORT)) {
            return R.drawable.ic_marker_airport;
        } else if (title.startsWith(ACCOUNTANTS_AUDITORS)) {
            return R.drawable.ic_marker_accounting;
        } else if (title.startsWith(ANIMAL_CARE)) {
            return R.drawable.ic_marker_animal_care;
        } else if (title.startsWith(ATM)) {
            return R.drawable.ic_marker_atm;
        } else if (title.startsWith(BARS_CLUBS)) {
            return R.drawable.ic_marker_bar;
        } else if (title.startsWith(BUREAU_DE_CHANGE)) {
            return R.drawable.ic_marker_bureau;
        } else if (title.startsWith(BUSINESS_CONSULTING)) {
            return R.drawable.ic_marker_commercial;
        } else if (title.startsWith(BUTCHER)) {
            return R.drawable.ic_marker_butchery;
        } else if (title.startsWith(CAR_RENTALS)) {
            return R.drawable.ic_marker_car;
        } else if (title.startsWith(CINEMAS)) {
            return R.drawable.ic_marker_cinema;
        } else if (title.startsWith(CLOTHING_APPAREL)) {
            return R.drawable.ic_marker_shopping;
        } else if (title.startsWith(COMMERICAL_WARES)) {
            return R.drawable.ic_marker_commercial;
        } else if (title.startsWith(AUTO)) {
            return R.drawable.ic_marker_auto;
        } else if (title.startsWith(BARS_CLUBS1)) {
            return R.drawable.ic_marker_bar;
        } else if (title.startsWith(PRINTERS)) {
            return R.drawable.ic_marker_commercial_printers;
        } else if (title.startsWith(EDUCATION)) {
            return R.drawable.ic_marker_education;
        } else if (title.startsWith(FUEL_STATIONS)) {
            return R.drawable.ic_marker_fuel;
        } else if (title.startsWith(FURNITURE)) {
            return R.drawable.ic_marker_furniture;
        } else if (title.startsWith(GIFT_SHOP)) {
            return R.drawable.ic_marker_marker_gift_shop;
        } else if (title.startsWith(GOVERNMENT_OFFICES)) {
            return R.drawable.ic_marker_government_office;
//        } else if (title.startsWith("Hardware & Construction")) {return R.drawable.ic_marker_ ;
        } else if (title.startsWith(HOSPITALS_AND_CLINICS)) {
            return R.drawable.ic_marker_hospital;
        } else if (title.startsWith(RESTURANTS)) {
            return R.drawable.ic_marker_restaurant;
        } else if (title.startsWith(HOTELS_LODGING)) {
            return R.drawable.ic_marker_hotel;
        } else if (title.startsWith(HOUSEWARES)) {
            return R.drawable.ic_marker_housewares;
        } else if (title.startsWith(INSURANCE)) {
            return R.drawable.ic_marker_insurance;
        } else if (title.startsWith(INTERIOR_DESIGN)) {
            return R.drawable.ic_marker_interior;
        } else if (title.startsWith(LEGAL_ADVOCATES)) {
            return R.drawable.ic_marker_legal;
        } else if (title.startsWith(PHARMACIES)) {
            return R.drawable.ic_marker_pharmacy;
        } else if (title.startsWith(POINTS_OF_INTEREST)) {
            return R.drawable.ic_marker_point_of_interst;
        } else if (title.startsWith(POST_OFFICES)) {
            return R.drawable.ic_marker_postoffice;
        } else if (title.startsWith(REAL_ESTATE)) {
            return R.drawable.ic_marker_real_estate;
        } else if (title.startsWith(RECREATION)) {
            return R.drawable.ic_marker_recreation;
        } else if (title.startsWith(SECURITY)) {
            return R.drawable.ic_marker_security;
        } else if (title.startsWith(SHOPPING_CENTERS)) {
            return R.drawable.ic_marker_shopping;
        } else if (title.startsWith(SPA_WELLNESS)) {
            return R.drawable.ic_marker_spa;
        } else if (title.startsWith(SOFTWARE_INTERNET_PROVIDERS)) {
            return R.drawable.ic_marker_software_and_internet;
        } else if (title.startsWith(SUPERMARKETS_GROCERY)) {
            return R.drawable.ic_marker_supermarket;
        } else if (title.startsWith(TAXI_SHUTTLES)) {
            return R.drawable.ic_marker_taxi;
        } else if (title.startsWith(THEATER_ARTS)) {
            return R.drawable.ic_marker_threatre;
        } else if (title.startsWith(SAFARI_TOURISM)) {
            return R.drawable.ic_marker_tourism_safari;
        } else if (title.startsWith(TRAVEL_AGENCY)) {
            return R.drawable.ic_marker_travel_agent;
        } else if (title.startsWith(MISCELLANEOUS)) {
            return R.drawable.ic_marker_micsellaneous;
        } else {
            AppLog.e(TAG, "Catagory icon for map is missing for :" + title);
            return R.drawable.ic_marker_micsellaneous;

        }


    }
}
