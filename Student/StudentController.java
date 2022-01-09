package Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// //This class will serve as a API layer.

//This class will serve as a API layer.
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService; // it is reference to student service
    // this will ask for constructor hence below is the constructor

    @Autowired // It will let to know above "StudentService" is injected into below constructor b/c we are not using "this.studentService= new StudentService()" and we should always try to avoid using "new" .
    public StudentController(StudentService studentService) {  // constructor will not work b/c we don't have instance of "StudentService so autowired
        this.studentService = studentService;
    }


    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public void registerStudent( @RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        studentService.updateStudent(studentId,name,email);
    }
}
