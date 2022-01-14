package salvador.caetano.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import salvador.caetano.backend.view.model.PrestacaoMensal;


@Controller
@RequestMapping("/prestacao-mensal")
public class WebPrestacaoMensalController {

    @GetMapping("/calcular")
    public ModelAndView getCalcular() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("prestacaoMensal", PrestacaoMensal.builder().build());
        modelAndView.setViewName("prestacao_mensal/calcular");
        return modelAndView;
    }


}
