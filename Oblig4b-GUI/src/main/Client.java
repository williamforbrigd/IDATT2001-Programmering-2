package main;

import members.BasicMember;
import members.BonusMember;
import members.MemberArchive;
import members.Personals;

import java.time.LocalDate;
import java.util.ArrayList;

public class Client {
    public static void main(String[]args) {
        Client client = new Client();
        client.addAllMembers();
    }

    public ArrayList<BonusMember> addAllMembers() {
        MemberArchive archive = new MemberArchive();

        Personals ole = new Personals("Olsen", "Ole", "ole.olsen@dot.com", "ole");
        Personals tove = new Personals("Hansen", "Tove", "tove.hansen@dot.com", "tove");
        LocalDate testdato = LocalDate.of(2008, 2, 10);
        LocalDate testDato1 = LocalDate.of(2006, 2, 15);
        LocalDate testDato2 = LocalDate.of(2007,3,5);

        archive.newMember(ole, testDato2);
        archive.newMember(tove, testDato2);

        System.out.println(archive.findPoints(archive.getMember(ole).getMemberNo(), ole.getPassword()));
        System.out.println(archive.findPoints(archive.getMember(tove).getMemberNo(), tove.getPassword()));

        archive.registerPoints(archive.getMember(ole).getMemberNo(), 50000);
        archive.registerPoints(archive.getMember(tove).getMemberNo(), 100000);
        System.out.println(archive.toString());

        archive.checkMembers3(testdato);
        System.out.println(archive.toString());

        archive.registerPoints(archive.getMember(ole).getMemberNo(), 50000);
        archive.checkMembers3(testdato);
        System.out.println(archive.toString());


        archive.subtractPoints(archive.getMember(ole).getMemberNo(), 100000);
        archive.subtractPoints(archive.getMember(tove).getMemberNo(), 90000);
        archive.checkMembers3(testdato);
        System.out.println(archive.toString());
        System.out.println(archive.findPoints(archive.getMember(ole).getMemberNo(), ole.getPassword()));
        System.out.println(archive.findPoints(archive.getMember(tove).getMemberNo(), tove.getPassword()));

        //System.out.println(archive.getMember(ole).toString());
        //System.out.println(archive.getMember(tove).toString());

        return archive.getMembers();
    }
}
