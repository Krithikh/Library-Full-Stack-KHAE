package self.learning.backend.lib.mgmt.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import self.learning.backend.lib.mgmt.dto.request.AuthorCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.AuthorUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;
import self.learning.backend.lib.mgmt.service.CreateAuthorService;
import self.learning.backend.lib.mgmt.service.DeleteAuthorService;
import self.learning.backend.lib.mgmt.service.SearchAuthorService;
import self.learning.backend.lib.mgmt.service.UpdateAuthorService;
import self.learning.backend.lib.mgmt.service.presenter.AuthorViewService;

@Controller
@RequestMapping("/library/authors")
public class AuthorViewController {

    @Autowired private AuthorViewService viewService;
    @Autowired private CreateAuthorService createService;
    @Autowired private UpdateAuthorService updateService;
    @Autowired private DeleteAuthorService deleteService;
    @Autowired private SearchAuthorService searchService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("authors", viewService.findAll());
        model.addAttribute("searchText", "");
        return "author/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam String text, Model model) {
        model.addAttribute("authors", searchService.searchAuthor(text));
        model.addAttribute("searchText", text);
        return "author/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("author", new AuthorCreateRequestDto());
        model.addAttribute("mode", "create");
        return "author/form";
    }

    @PostMapping
    public String create(@ModelAttribute("author") AuthorCreateRequestDto request) {
        AuthorResponseDto created = createService.createAuthor(request);
        return "redirect:/library/authors/" + created.getAuthorId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("author", viewService.findById(id));
        return "author/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        AuthorResponseDto current = viewService.findById(id);
        AuthorUpdateRequestDto request = new AuthorUpdateRequestDto();
        if (current != null) {
            request.setAuthorCode(current.getAuthorCode());
            request.setAuthorName(current.getAuthorName());
        }
        model.addAttribute("author", request);
        model.addAttribute("authorId", id);
        model.addAttribute("mode", "edit");
        return "author/form";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @ModelAttribute("author") AuthorUpdateRequestDto request) {
        updateService.updateAuthor(id, request);
        return "redirect:/library/authors/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deactivate(@PathVariable Long id) {
        deleteService.deleteAuthor(id);
        return "redirect:/library/authors";
    }
}
