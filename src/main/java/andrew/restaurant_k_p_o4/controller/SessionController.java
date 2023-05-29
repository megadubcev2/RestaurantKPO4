package andrew.restaurant_k_p_o4.controller;

import andrew.restaurant_k_p_o4.model.SessionDTO;
import andrew.restaurant_k_p_o4.service.SessionService;
import andrew.restaurant_k_p_o4.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(final SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("sessions", sessionService.findAll());
        return "session/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("session") final SessionDTO sessionDTO) {
        return "session/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("session") @Valid final SessionDTO sessionDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "session/add";
        }
        sessionService.create(sessionDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("session.create.success"));
        return "redirect:/sessions";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id, final Model model) {
        model.addAttribute("session", sessionService.get(id));
        return "session/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id,
            @ModelAttribute("session") @Valid final SessionDTO sessionDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "session/edit";
        }
        sessionService.update(id, sessionDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("session.update.success"));
        return "redirect:/sessions";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Integer id,
            final RedirectAttributes redirectAttributes) {
        sessionService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("session.delete.success"));
        return "redirect:/sessions";
    }

}
