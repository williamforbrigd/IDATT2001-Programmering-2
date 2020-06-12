package members;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class BonusMember {
    public static final double FACTOR_SILVER = 1.2;
    public static final double FACTOR_GOLD = 1.5;
    private final int memberNo;
    private final Personals personals;
    private final LocalDate enrolledDate;
    private int points = 0;


    /**
     * Instantiates a new bonus member.
     * @param memberNo the member number
     * @param personals the personals
     * @param enrolledDate the enrolled date.
     * @throws IllegalArgumentException if membernumber, personals or enrolleddate is not valid or is null.
     */
    public BonusMember(int memberNo, Personals personals, LocalDate enrolledDate) throws IllegalArgumentException {
        if(memberNo <= 0) {
            throw new IllegalArgumentException("The member number is invalid");
        } else if(personals == null) {
            throw new IllegalArgumentException("Personals is invalid");
        } else if(enrolledDate == null) {
            throw new IllegalArgumentException("Enrolled date is invalid");
        }
        this.memberNo = memberNo;
        this.personals = personals;
        this.enrolledDate = enrolledDate;
    }

    public BonusMember(int memberNo, Personals personals, LocalDate enrolledDate, int points) {
        this.memberNo = memberNo;
        this.personals = personals;
        this.enrolledDate = enrolledDate;
        this.points = points;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public Personals getPersonals() {
        return personals;
    }

    public LocalDate getEnrolledDate() {
        return enrolledDate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points += points;
    }

    /**
     * Finds the qualification points for the member.
     * @param date which is the date that is checked to the enrolled date.
     * @return points, the points that the member is qualified for.
     * @throws IllegalArgumentException if the date the is being sent in as a parameter is invalid.
     * It is always essential to check that the input parameters are valid.
     */

    public int findQualificationPoints(LocalDate date) throws IllegalArgumentException {
        if(date == null) {
            throw new IllegalArgumentException("Date parameter is null");
        }
        long daysBetween = ChronoUnit.DAYS.between(enrolledDate, date);
        if(daysBetween <= 365) return points;
        return 0;
    }

    /**
     * Checks is the password does not match any other password.
     * @param password, the password that is checked.
     * @return true if the password does not match, and false otherwise.
     * @throws IllegalArgumentException if the password is null.
     */
    public boolean okPassword(String password) throws IllegalArgumentException {
        if(password == null ||password.equals("")) {
            throw new IllegalArgumentException("Password is invalid");
        }
        return personals.okPassword(password);
    }

    public void registerPoints(int points) {
        this.points+=points;
    }

    public void subtractPoints(int points) {
        this.points -= points;
    }

    @Override
    public String toString() {
        return "BonusMember{" +
                "memberNo=" + memberNo +
                ", personals=" + personals.toString() +
                ", enrolledDate=" + enrolledDate.toString() +
                ", points=" + points +
                '}';
    }
}
