package org.example.repository;

import org.example.model.Student;
import org.junit.*;

import static org.junit.Assert.*;

public class StudentRepositoryTest {
    private static StudentRepository repository;
    @BeforeClass
    public static void beforeClass() throws Exception {
        repository = new StudentRepository();
    }
    @AfterClass
    public static void afterClass() throws Exception {
        repository.closeDb();
    }
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addStudent() {
        Student student = new Student();
        student.setLastname("Red");
        student.setFirstname("Alan");

        repository.addStudent(student);

        assertNotNull(student.getId());
        assertEquals(student.getId(), 1L);
    }

    @Test
    public void find() {
        Student student = new Student();
        student.setLastname("Red");
        student.setFirstname("Alan");
        repository.addStudent(student);

        student = repository.find(student.getId());

        assertNotNull(student);
        assertNotNull(student.getId());
        assertEquals("Red", student.getLastname());
    }

    @Test
    public void update() {
        Student student = new Student();
        student.setLastname("Red");
        student.setFirstname("Alan");
        repository.addStudent(student);

        student.setLastname("Green");
        student = repository.update(student);

        assertNotNull(student);
        assertEquals("Green", student.getLastname());
        assertEquals("Alan", student.getFirstname());
    }

    @Test
    public void delete() {
        Student student = new Student();
        student.setLastname("Red");
        student.setFirstname("Alan");
        repository.addStudent(student);

        repository.delete(student);

        student = repository.find(student.getId());

        assertNull(student);

    }
}