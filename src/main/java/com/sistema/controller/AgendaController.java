package com.sistema.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.model.Informacao;
import com.sistema.model.Observacao;
import com.sistema.repository.Dados;

@Controller
@RequestMapping("/")
public class AgendaController {

	@Autowired
	private Dados dados;

	@RequestMapping(method = RequestMethod.GET)
	public String agendamentos(Model model) {
		model.addAttribute("agendamentos", dados.findAll());
		return "Agendamentos";
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public String novo(Informacao informacao, Model model) {
		model.addAttribute("informacao", informacao);
		return "Novo";
	}

	@Transactional
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String novo(Informacao informacao, RedirectAttributes attributes) {
		dados.save(informacao);
		attributes.addFlashAttribute("mensagem", "Agendamento efetuado com sucesso!");
		return "redirect:/";
	}

	@RequestMapping(value = "/concluido/{id}", method = RequestMethod.GET)
	public String concluido(@PathVariable("id") Long id, RedirectAttributes attributes) {
		attributes.addFlashAttribute("mensagem",
				"O agendamento do cliente " + dados.findOne(id).getNome() + " foi marcado como concluído com sucesso!");
		dados.delete(id);
		return "redirect:/";
	}

	@ModelAttribute("observacoes")
	public List<Observacao> todas() {
		return Arrays.asList(Observacao.values());
	}
}
