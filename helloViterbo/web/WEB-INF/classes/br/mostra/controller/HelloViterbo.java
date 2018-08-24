@Controller
public class HelloViterboController {

    @RequestMapping("/HelloViterbo")
    public String execute() {
        System.out.println("Executando a lógica com Spring MVC");
        return "ok";
    }
}