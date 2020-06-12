package members;

import java.time.LocalDate;

public class BasicMember extends BonusMember {

    public BasicMember(int memberNo, Personals personals, LocalDate enrolledDate, int points) {
        super(memberNo, personals, enrolledDate);
        super.registerPoints(points);
    }

    public BasicMember(int memberNo, Personals personals, LocalDate enrolledDate) {
        super(memberNo, personals, enrolledDate);
    }

    @Override
    public String toString() {
        return "BasicMember{" +
                "memberNo=" + super.getMemberNo() +
                ", personals=" + super.getPersonals() +
                ", enrolledDate=" + super.getEnrolledDate() +
                ", points=" + super.getPoints() +
                '}';
    }
}
