package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DesafioMeuTimeApplication implements MeuTimeInterface {
    List<Team> teams = new ArrayList<>();

    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) throws IdentificadorUtilizadoException {
        for (Team i : teams) {
            if (i.getId() == id) {
                throw new IdentificadorUtilizadoException();
            }
        }

        teams.add(new Team(id, nome, dataCriacao,corUniformePrincipal, corUniformeSecundario));
    }

    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        teams.stream().forEach(team -> {
            for (Player i : team.getSoccerPlayers()) {
                if (i.getId() == id) {
                    throw new IdentificadorUtilizadoException();
                }
            }
        });

        Player newPlayer = new Player(id, idTime, nome, dataNascimento, nivelHabilidade, salario);

        List<Team> soccerTeam = teams.stream()
            .filter(team -> team.getId() == idTime)
            .collect(Collectors.toList());

        if (soccerTeam.size() == 0) {
            throw new TimeNaoEncontradoException();
        }

        soccerTeam
            .get(0)
            .setSoccerPlayers(newPlayer);
    }

    public void definirCapitao(Long idJogador) {
        List<Team> soccerTeam = new ArrayList<>();

        teams.stream().forEach(team -> {
            for (Player i : team.getSoccerPlayers()) {
                if (i.getId() == idJogador) {
                    soccerTeam.add(team);
                }
            }
        });

        if (soccerTeam.size() == 0) {
            throw new JogadorNaoEncontradoException();
        }

        soccerTeam.get(0).setTeamCaptain(idJogador);
    }

    public Long buscarCapitaoDoTime(Long idTime) {
        List<Team> soccerTeam = teams.stream()
            .filter(team -> team.getId() == idTime)
            .collect(Collectors.toList());

        if (soccerTeam.size() == 0) {
            throw new TimeNaoEncontradoException();
        }

        if (soccerTeam.get(0).getTeamCaptain() == null) {
            throw new CapitaoNaoInformadoException();
        }

        return soccerTeam.get(0).getTeamCaptain();
    }

    public String buscarNomeJogador(Long idJogador) {
       List<String> listName = new ArrayList<>();

       teams.stream().forEach(team -> {
           for (Player i : team.getSoccerPlayers()) {
               if (i.getId() == idJogador) {
                   listName.add(i.getNome());
               }
           }
       });

       if (listName.size() == 0) {
           throw new JogadorNaoEncontradoException();
       }

       return listName.get(0);
    }

    public String buscarNomeTime(Long idTime) {
        List<Team> teamsList = teams.stream()
            .filter(team -> team.getId() == idTime)
            .collect(Collectors.toList());

        if (teamsList.size() == 0) {
            throw new TimeNaoEncontradoException();
        }

        return teamsList.get(0).getNome();
    }

    public List<Long> buscarJogadoresDoTime(Long idTime) {
        List<Long> playerIdentifier = new ArrayList<>();

        List<Team> listTeam = teams.stream()
            .filter(team -> team.getId() == idTime)
            .collect(Collectors.toList());

        if (listTeam.size() == 0) {
            throw new TimeNaoEncontradoException();
        }

        Team soccerTeam = listTeam.get(0);

        for (Player i : soccerTeam.getSoccerPlayers()) {
            playerIdentifier.add(i.id);
        }

        return playerIdentifier;
    }

    public Long buscarMelhorJogadorDoTime(Long idTime) {
        List<Long> bestPlayer = new ArrayList<>();

        List<Team> soccerTeam = teams.stream()
            .filter(team -> team.getId() == idTime)
            .collect(Collectors.toList());

        if (soccerTeam.size() == 0) {
            throw new TimeNaoEncontradoException();
        }

        List<Player> players = soccerTeam.get(0)
            .getSoccerPlayers();

        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                if (p1.getNivelHabilidade() > p2.getNivelHabilidade()) {
                    return -1;
                }
                if (p1.getNivelHabilidade() < p2.getNivelHabilidade()) {
                    return 1;
                }
                return 0;
            }
        });

        players.stream().forEach(player -> {
            for (Player i : players) {
                bestPlayer.add(i.getId());
            }
        });

        return bestPlayer.get(0);
    }

    public Long buscarJogadorMaisVelho(Long idTime) {
        List<Team> soccerTeam = teams.stream()
            .filter(team -> team.getId() == idTime)
            .collect(Collectors.toList());

        if (soccerTeam.size() == 0) {
            throw new TimeNaoEncontradoException();
        }

        List<Player> players = soccerTeam.get(0).getSoccerPlayers();

        players.sort((p1, p2) -> p1.getDataNascimento().compareTo(p2.getDataNascimento()));

        return players.get(0).getId();
    }

    public List<Long> buscarTimes() {
        List<Long> identifiers = new ArrayList<>();

        for (Team i : teams) {
            identifiers.add(i.id);
        }

        return identifiers;
    }

    public Long buscarJogadorMaiorSalario(Long idTime) {
        List<Team> soccerTeam = teams.stream()
            .filter(team -> team.getId() == idTime)
            .collect(Collectors.toList());

        if (soccerTeam.size() == 0) {
            throw new TimeNaoEncontradoException();
        }

        List<Player> players = soccerTeam.get(0).getSoccerPlayers();
        players.sort((p1, p2) -> p2.getSalario().compareTo(p1.getSalario()));

        return players.get(0).getId();
    }

    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        List<Player> player = new ArrayList<>();

        teams.stream().forEach(team -> {
            for (Player i : team.getSoccerPlayers()) {
                if (i.getId() == idJogador) {
                    player.add(i);
                }
            }
        });

        if (player.size() == 0) {
            throw new JogadorNaoEncontradoException();
        }

        return player.get(0).getSalario();
    }

    public List<Long> buscarTopJogadores(Integer top) {
        List<Player> players = new ArrayList<>();
        List<Long> identifierPlayers = new ArrayList<>();

        teams.stream().forEach(team -> {
            if (team.getSoccerPlayers().size() != 0) {
                for (Player i : team.getSoccerPlayers()) {
                    players.add(i);
                }
            }
        });

        if (players.size() != 0) {
            Collections.sort(players, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    if (p1.getNivelHabilidade() > p2.getNivelHabilidade()) {
                        return -1;
                    }
                    if (p1.getNivelHabilidade() < p2.getNivelHabilidade()) {
                        return 1;
                    }
                    return 0;
                }
            });

            for (int i = 0; i < top; i++) {
                identifierPlayers.add(players.get(i).getId());
            }
        }

        return identifierPlayers;
    }
}
