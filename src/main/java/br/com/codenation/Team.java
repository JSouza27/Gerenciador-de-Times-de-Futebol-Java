package br.com.codenation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Team {
  Long id;
  String nome;
  LocalDate dataCriacao;
  String corUniformePrincipal;
  String corUniformeSecundario;
  Long teamCaptain = null;
  List<Player> soccerPlayers = new ArrayList<>();

  public Team(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
    this.id = id;
    this.nome = nome;
    this.dataCriacao = dataCriacao;
    this.corUniformePrincipal = corUniformePrincipal;
    this.corUniformeSecundario = corUniformeSecundario;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDate getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(LocalDate dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public String getCorUniformePrincipal() {
    return corUniformePrincipal;
  }

  public void setCorUniformePrincipal(String corUniformePrincipal) {
    this.corUniformePrincipal = corUniformePrincipal;
  }

  public String getCorUniformeSecundario() {
    return corUniformeSecundario;
  }

  public void setCorUniformeSecundario(String corUniformeSecundario) {
    this.corUniformeSecundario = corUniformeSecundario;
  }

  public List<Player> getSoccerPlayers() {
    return soccerPlayers;
  }

  public void setSoccerPlayers(Player player) {
    this.soccerPlayers.add(player);
  }

  public Long getTeamCaptain() {
    return teamCaptain;
  }

  public void setTeamCaptain(Long teamCaptain) {
    this.teamCaptain = teamCaptain;
  }
}
