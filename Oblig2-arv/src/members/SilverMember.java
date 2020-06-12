package members;

import java.time.LocalDate;

public class SilverMember extends BonusMember {

    public SilverMember(int memberNo, Personals personals, LocalDate enrolledDate, int points) {
        super(memberNo,personals, enrolledDate);
        super.registerPoints(points);
    }

    @Override
    public void registerPoints(int points) {
        int sum = (int)Math.round(points*FACTOR_SILVER);
        super.registerPoints(sum);
    }

    @Override
    public String toString() {
        return "SilverMember{" +
                "memberNo=" + super.getMemberNo() +
                ", personals=" + super.getPersonals() +
                ", enrolledDate=" + super.getEnrolledDate() +
                ", points=" + super.getPoints() +
                '}';
    }
}
