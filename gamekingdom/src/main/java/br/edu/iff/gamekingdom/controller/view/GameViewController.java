package br.edu.iff.gamekingdom.controller.view;

import br.edu.iff.gamekingdom.entities.Game;
import br.edu.iff.gamekingdom.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/games")
public class GameViewController {

    @Autowired
    private GameService gameService;

    @GetMapping("/list")
    public String showGameList(Model model) {
        List<Game> games = gameService.listAllGames();
        model.addAttribute("games", games);
        return "gameList";
    }

    @GetMapping("/add")
    public String showAddGameForm(Model model) {
        model.addAttribute("game", new Game());
        return "addGame";
    }

    @PostMapping("/add")
    public String addGame(@ModelAttribute("game") Game game) {
        gameService.addGame(game.getTitle(), game.getPrice());
        return "redirect:/games/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateGameForm(@PathVariable("id") Long id, Model model) {
        Game game = gameService.findGameById(id);
        model.addAttribute("game", game);
        return "updateGame";
    }

    @PostMapping("/update/{id}")
    public String updateGame(@PathVariable("id") Long id, @ModelAttribute("game") Game game) {
        gameService.updateGame(id, game.getTitle(), game.getPrice());
        return "redirect:/games/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable("id") Long id) {
        gameService.deleteGame(id);
        return "redirect:/games/list";
    }
}
