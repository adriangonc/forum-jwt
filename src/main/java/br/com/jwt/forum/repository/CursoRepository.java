package br.com.jwt.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jwt.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
