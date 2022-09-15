package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private boolean outOfTown;

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int age;

    private WareHouse wareHouse;

    @XmlElementWrapper(name = "langs")
    @XmlElement(name = "lang")
    private String[] langs;

    public Student() {

    }

    public Student(boolean outOfTown, String name, int age, WareHouse wareHouse, String... langs) {
        this.outOfTown = outOfTown;
        this.name = name;
        this.age = age;
        this.wareHouse = wareHouse;
        this.langs = langs;
    }

    @Override
    public String toString() {
        return "Student{"
                + "outoftown=" + outOfTown
                + ", name=" + name
                + ", age=" + age
                + ", warehouse=" + wareHouse
                + ", langs=" + Arrays.toString(langs)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final Student student = new Student(true, "Andrew Johnson", 21,
                new WareHouse("https://github.com/CodePlay"),
                "C#", "Java", "JS");

        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(student, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
