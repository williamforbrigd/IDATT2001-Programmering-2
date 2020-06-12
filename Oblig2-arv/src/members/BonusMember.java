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


    public BonusMember(int memberNo, Personals personals, LocalDate enrolledDate) {
        this.memberNo = memberNo;
        this.personals = personals;
        this.enrolledDate = enrolledDate;
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

    public int findQualificationPoints(LocalDate date) {
        if(date != null) {
            long daysBetween = ChronoUnit.DAYS.between(enrolledDate, date);
            if(daysBetween <= 365) return points;
        }
        return 0;
    }

    public boolean okPassword(String password) {
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
