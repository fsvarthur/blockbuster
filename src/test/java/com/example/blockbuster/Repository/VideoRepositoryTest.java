package com.example.blockbuster.Repository;

import com.example.blockbuster.Model.Categoria;
import com.example.blockbuster.Model.Video;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class VideoRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void cadastrarVideo(){
        Categoria categoria = this.salvarCategoria();
        Video video = this.salvarVideo(categoria);

        Assert.assertNotNull(video);
        Assert.assertTrue(video.getId()>0);
        Long idVideo = video.getId();

        Video videoConsultado = videoRepository.getReferenceById(idVideo);
        Assert.assertNotNull(videoConsultado.getCategoria());
        Assert.assertTrue(videoConsultado.getCategoria().getId().longValue() == categoria.getId().longValue());

    }

    //cadastrarVideoSemCat
    @Test
    public void setVideoWithoutCategoria(){
        Video video = this.salvarVideo(null);

        Assert.assertNotNull(video);
        Assert.assertTrue(video.getId()>0);
        Long idVideo = video.getId();

        Video videoConsultado = videoRepository.getReferenceById(idVideo);
        Assert.assertNull(videoConsultado.getCategoria());
    }

    //alterarVideo
    @Test
    public void updateVideo(){
        Categoria categoria = this.salvarCategoria();
        Video video = this.salvarVideo(categoria);

        Assert.assertNotNull(video);
        Assert.assertTrue(video.getId()>0);
        Long idVideo = video.getId();

        String titulo = video.getTitulo();
        video.setTitulo("AAAAAAAA");
        videoRepository.save(video);

        Video videoConsultado = videoRepository.getReferenceById(idVideo);
        Assert.assertTrue(videoConsultado.getTitulo() != titulo);
    }

    //consultarVideoByCategoria
    @Test
    public void findVideoByCategoria(){
        Categoria categoria = this.salvarCategoria();
        Video video = this.salvarVideo(categoria);

        List<Video> videos = videoRepository.findByCategoriaId(categoria.getId());
        Assert.assertNotNull(videos);
        Assert.assertTrue(videos.get(0).getCategoria().getId().longValue() == categoria.getId());
    }

    //consultarVideoByTitulo
    @Test
    public void findVideoByTitulo(){
        Categoria categoria =null;
        Video video = this.salvarVideo(categoria);

        List<Video> videos = videoRepository.findByTitulo("titulo video teste");
        Assert.assertNotNull(videos);
        Assert.assertTrue(videos.size()>0);
        Assert.assertTrue(videos.get(0).getTitulo().equals("titulo video teste"));
    }

    //listarVideo

    @Test
    public void listVideos() {
        Categoria categoria = null;
        Video video = this.salvarVideo(categoria);

        List<Video> videos = videoRepository.findAll();
        Assert.assertTrue(videos.size()>0);
    }

    //excluirVideo
    @Test
    public void deleteVideo(){
        Categoria categoria = null;
        Video video = this.salvarVideo(null);
        Long ID_VIDEO = video.getId();

        Optional<Video> videoSalvo = videoRepository.findById(ID_VIDEO);
        Assert.assertTrue(videoSalvo.isPresent());
        Assert.assertNotNull(videoSalvo.get());
        Assert.assertTrue(videoSalvo.get().getId().equals(ID_VIDEO));

        videoRepository.delete(video);

        Optional<Video> videoProcurado = videoRepository.findById(ID_VIDEO);
        Assert.assertFalse(videoProcurado.isPresent());

    }

    private Video salvarVideo(Categoria categoria) {
        Video video = new Video();
        video.setTitulo("titulo video teste");
        video.setDescricao("Descricao video teste");
        video.setUrl("localhost:8080/1");
        video.setCategoria(categoria);

        Video newVideo = videoRepository.save(video);
        return newVideo;
    }

    private Categoria salvarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setCor("#FFFFFF");
        categoria.setTitulo("Adventure");

        Categoria newCategoria = categoriaRepository.save(categoria);
        return newCategoria;
    }
}
