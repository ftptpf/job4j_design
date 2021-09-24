package ru.job4j.ood.isp.wrong;

/**
 * Нарушение принципа ISP. Классы MediaPlayer и AudioPlayer реализующие данный интерфейс
 * не будут в полной мере использовать объявленные методы.
 * Для AudioPlayer метод playVideo() не нужен.
 * Решением будет создание отдельных интерфейсов для аудио и видео плееров.
 */
public interface Player {
    void playAudio();
    void playVideo();
}
