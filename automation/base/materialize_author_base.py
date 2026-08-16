from pathlib import Path

ROOT = Path('.')
PKG = ROOT / 'backend/backend.lib.mgmt/src/main/java/self/learning/backend/lib/mgmt'
TPL = ROOT / 'backend/backend.lib.mgmt/src/main/resources/templates/author'

files = {
PKG / 'service/presenter/PresenterAuthorListService.java': r'''package self.learning.backend.lib.mgmt.service.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import self.learning.backend.lib.mgmt.dao.AuthorDao;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;
import self.learning.backend.lib.mgmt.mapper.AuthorDtoDoMapper;

@Service
public class PresenterAuthorListService {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private AuthorDtoDoMapper mapper;

    public List<AuthorResponseDto> listAuthors() {
        return authorDao.findAllCurrent()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
''',
PKG / 'controller/rest/AuthorRestController.java': r'''package self.learning.backend.lib.mgmt.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import self.learning.backend.lib.mgmt.dto.ApiResponse;
import self.learning.backend.lib.mgmt.dto.request.AuthorCreateRequestDto;
import self.learning.backend.lib.mgmt.dto.request.AuthorUpdateRequestDto;
import self.learning.backend.lib.mgmt.dto.response.AuthorResponseDto;
import self.learning.backend.lib.mgmt.service.CreateAuthorService;
import self.learning.backend.lib.mgmt.service.DeleteAuthorService;
import self.learning.backend.lib.mgmt.service.ReadAuthorService;
import self.learning.backend.lib.mgmt.service.SearchAuthorService;
import self.learning.backend.lib.mgmt.service.UpdateAuthorService;
import self.learning.backend.lib.mgmt.service.presenter.PresenterAuthorListService;

@RestController
@RequestMapping("/rest/authors")
public class AuthorRestController {

    @Autowired private PresenterAuthorListService presenterListService;
    @Autowired private CreateAuthorService createService;
    @Autowired private ReadAuthorService readService;
    @Autowired private UpdateAuthorService updateService;
    @Autowired private DeleteAuthorService deleteService;
    @Autowired private SearchAuthorService searchService;

    @GetMapping
    public ApiResponse<List<AuthorResponseDto>> list() {
        return ApiResponse.success("P03", "Author List Retrieved Successfully",
                presenterListService.listAuthors());
    }

    @PostMapping
    public ApiResponse<AuthorResponseDto> create(@RequestBody AuthorCreateRequestDto request) {
        return ApiResponse.success("11", "Author Created Successfully",
                createService.createAuthor(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<AuthorResponseDto> read(@PathVariable Long id) {
        return ApiResponse.success("12", "Author Read Successfully",
                readService.readAuthor(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<AuthorResponseDto> update(
            @PathVariable Long id,
            @RequestBody AuthorUpdateRequestDto request) {
        return ApiResponse.success("13", "Author Updated Successfully",
                updateService.updateAuthor(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<AuthorResponseDto> delete(@PathVariable Long id) {
        return ApiResponse.success("14", "Author Deactivated Successfully",
                deleteService.deleteAuthor(id));
    }

    @GetMapping("/search")
    public ApiResponse<List<AuthorResponseDto>> search(@RequestParam String text) {
        return ApiResponse.success("15", "Author Search Completed Successfully",
                searchService.searchAuthor(text));
    }
}
''',
PKG / 'controller/view/AuthorViewController.java': r'''package self.learning.backend.lib.mgmt.controller.view;

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
import self.learning.backend.lib.mgmt.service.ReadAuthorService;
import self.learning.backend.lib.mgmt.service.SearchAuthorService;
import self.learning.backend.lib.mgmt.service.UpdateAuthorService;
import self.learning.backend.lib.mgmt.service.presenter.PresenterAuthorListService;

@Controller
@RequestMapping("/library/authors")
public class AuthorViewController {

    @Autowired private PresenterAuthorListService presenterListService;
    @Autowired private CreateAuthorService createService;
    @Autowired private ReadAuthorService readService;
    @Autowired private UpdateAuthorService updateService;
    @Autowired private DeleteAuthorService deleteService;
    @Autowired private SearchAuthorService searchService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("authors", presenterListService.listAuthors());
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
        model.addAttribute("author", readService.readAuthor(id));
        return "author/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        AuthorResponseDto current = readService.readAuthor(id);
        AuthorUpdateRequestDto request = new AuthorUpdateRequestDto();
        request.setAuthorCode(current.getAuthorCode());
        request.setAuthorName(current.getAuthorName());
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
''',
TPL / 'list.html': r'''<!doctype html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>Authors - Thymeleaf</title>
  <style>
    body{font-family:Arial,sans-serif;margin:2rem;max-width:1100px} table{border-collapse:collapse;width:100%;margin-top:1rem} th,td{border:1px solid #ccc;padding:.55rem;text-align:left} .actions{display:flex;gap:.5rem;align-items:center} a,button{padding:.35rem .6rem} .top{display:flex;justify-content:space-between;align-items:center;gap:1rem} form.search{display:flex;gap:.5rem}
  </style>
</head>
<body>
  <div class="top">
    <div><h1>Author Management - Thymeleaf</h1><p>Server-rendered Spring MVC page.</p></div>
    <a th:href="@{/library/authors/new}">Create Author</a>
  </div>
  <form class="search" th:action="@{/library/authors/search}" method="get">
    <input name="text" th:value="${searchText}" placeholder="Author code or name" required>
    <button type="submit">Search</button>
    <a th:href="@{/library/authors}">Clear</a>
  </form>
  <table>
    <thead><tr><th>ID</th><th>Code</th><th>Name</th><th>Active</th><th>Actions</th></tr></thead>
    <tbody>
      <tr th:each="author : ${authors}">
        <td th:text="${author.authorId}"></td>
        <td th:text="${author.authorCode}"></td>
        <td th:text="${author.authorName}"></td>
        <td th:text="${author.active}"></td>
        <td class="actions">
          <a th:href="@{/library/authors/{id}(id=${author.authorId})}">Read</a>
          <a th:href="@{/library/authors/{id}/edit(id=${author.authorId})}">Update</a>
          <form th:action="@{/library/authors/{id}/delete(id=${author.authorId})}" method="post">
            <button type="submit">Deactivate</button>
          </form>
        </td>
      </tr>
    </tbody>
  </table>
  <p><a th:href="@{/library}">Back to Library Home</a></p>
</body>
</html>
''',
TPL / 'form.html': r'''<!doctype html>
<html xmlns:th="http://www.thymeleaf.org">
<head><meta charset="UTF-8"><title>Author Form - Thymeleaf</title><style>body{font-family:Arial,sans-serif;margin:2rem;max-width:700px}label{display:block;margin-top:1rem}input{width:100%;padding:.55rem;box-sizing:border-box}.actions{margin-top:1.25rem;display:flex;gap:.75rem}</style></head>
<body>
  <h1 th:text="${mode == 'create'} ? 'Create Author - Thymeleaf' : 'Update Author - Thymeleaf'"></h1>
  <form th:object="${author}" th:action="${mode == 'create'} ? @{/library/authors} : @{/library/authors/{id}/edit(id=${authorId})}" method="post">
    <label>Author Code</label><input th:field="*{authorCode}" required>
    <label>Author Name</label><input th:field="*{authorName}" required>
    <div class="actions"><button type="submit">Save</button><a th:href="@{/library/authors}">Cancel</a></div>
  </form>
</body>
</html>
''',
TPL / 'detail.html': r'''<!doctype html>
<html xmlns:th="http://www.thymeleaf.org">
<head><meta charset="UTF-8"><title>Author Detail - Thymeleaf</title><style>body{font-family:Arial,sans-serif;margin:2rem;max-width:700px}dl{display:grid;grid-template-columns:180px 1fr;gap:.7rem}dt{font-weight:bold}.actions{margin-top:1.5rem;display:flex;gap:.75rem;align-items:center}</style></head>
<body>
  <h1>Read Author - Thymeleaf</h1>
  <dl>
    <dt>Author ID</dt><dd th:text="${author.authorId}"></dd>
    <dt>Author Code</dt><dd th:text="${author.authorCode}"></dd>
    <dt>Author Name</dt><dd th:text="${author.authorName}"></dd>
    <dt>Active</dt><dd th:text="${author.active}"></dd>
  </dl>
  <div class="actions">
    <a th:href="@{/library/authors/{id}/edit(id=${author.authorId})}">Update</a>
    <form th:action="@{/library/authors/{id}/delete(id=${author.authorId})}" method="post"><button type="submit">Deactivate</button></form>
    <a th:href="@{/library/authors}">Back to List</a>
  </div>
</body>
</html>
'''
}

for path, content in files.items():
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(content, encoding='utf-8')
    print('Wrote', path)
