public enum Gender {

    MALE,
    FEMALE;

    public String getGender (Gender gender){
        if ((gender.toString()).equalsIgnoreCase(MALE.toString())){
            return MALE.toString();
        }
        else if ((gender.toString()).equalsIgnoreCase(FEMALE.toString())){
            return  FEMALE.toString();
        }
        else {
            return null;
        }
    }
}
