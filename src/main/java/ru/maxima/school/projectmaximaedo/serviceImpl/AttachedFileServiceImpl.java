package ru.maxima.school.projectmaximaedo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.maxima.school.projectmaximaedo.dto.AttachedFileDto;
import ru.maxima.school.projectmaximaedo.mapper.AttachedFileMapper;
import ru.maxima.school.projectmaximaedo.model.AttachedFile;
import ru.maxima.school.projectmaximaedo.repository.FileRepository;
import ru.maxima.school.projectmaximaedo.service.AttachedFileService;
import ru.maxima.school.projectmaximaedo.utils.Constants;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class AttachedFileServiceImpl implements AttachedFileService {
    private final FileRepository fileRepository;
    private final MapperUtil mapperUtil;
    private final AttachedFileMapper fileMapper;

    @Autowired
    public AttachedFileServiceImpl(FileRepository fileRepository, MapperUtil mapperUtil, AttachedFileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.mapperUtil = mapperUtil;
        this.fileMapper = fileMapper;
    }

    @Override
    @Transactional
    public List<AttachedFileDto> getAll() {
        List<AttachedFile> files = fileRepository.findAllByIsRemovedIsFalseOrderByIdAsc();
        if (files == null || files.size() == 0) {
            return null;
        }
        return mapperUtil.mapList(files, AttachedFileDto.class);
    }

    @Override
    @Transactional
    public Boolean exists(Long id) {
        return fileRepository.existsById(id);
    }

    @Override
    @Transactional
    public AttachedFileDto getById(Long id) {
        AttachedFile file = fileRepository.findFileById(id).orElse(null);
        return file != null ? fileMapper.toDto(file) : null;
    }

    @Override
    @Transactional
    public Boolean create(AttachedFile file) {
        boolean isFilePresent = false;
        List<AttachedFile> filesFromDir = null,filesFromDb = null;
        if(file.getId() == null || file.getId() < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Введите id для создания файла");
        }
        if (file.getDescription().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Введите описание файла");
        }
        if (file == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Нет данных для создания файла");
        } else {
            try {
                filesFromDir = readFilesFromDir();
                for (int i = 0; i < filesFromDir.size(); i++) {
                    if (filesFromDir.get(i).getId() == file.getId()) {
                        filesFromDb = fileRepository.findAllByIsRemovedIsFalseOrderByIdAsc();
                        for (int j = 0; j < filesFromDb.size(); j++) {
                            if(filesFromDb.get(j).getName().equals(filesFromDir.get(i).getName())){
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "Такой файл уже существует в базе");
                            }
                        }
                        filesFromDir.get(i).setDescription(file.getDescription());
                        fileRepository.save(filesFromDir.get(i));
                        isFilePresent = true;
                    }
                }
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        e.getMessage());
            }
            if( isFilePresent == false){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "С такими данными нет файла в списке");
            }
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean update(AttachedFileDto fileDto, Long id) {
        if(fileDto == null){
            return true;
        }
        fileDto.setId(id);
        AttachedFile file = fileMapper.toEntity(fileDto);
        AttachedFile readFile = fileRepository.findFileById(id).orElse(null);
        if (readFile != null) {
            file.setRemoved(readFile.isRemoved());
            file.setSize(readFile.getSize());
            file.setMimeType(readFile.getMimeType());
            fileRepository.save(file);
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean safeDelete(Long id) {
        AttachedFile file = fileRepository.findFileById(id).orElse(null);
        if (file != null) {
            file.setRemoved(true);
            fileRepository.save(file);
            return false;
        }
        return true;
    }

    public List<AttachedFile> readFilesFromDir() throws IOException {
        List<AttachedFile> fileList = new ArrayList<>();
        List<String> files = Files.list(Paths.get(new File(Constants.SOURCE_FILES_PATH).getAbsolutePath()))
                .map(Path::toString)
                .toList();

        IntStream.range(0, files.size())
                .forEach(index -> {
                    File readFile = new File(files.get(index));
                    AttachedFile newFile = new AttachedFile();
                    newFile.setId((long) index);
                    newFile.setName(readFile.getName());
                    newFile.setSize(readFile.length());
                    newFile.setNameOfStorage(readFile.getAbsolutePath());
                    try {
                        newFile.setMimeType(Files.probeContentType(readFile.toPath()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    newFile.setDescription("description");
                    fileList.add(newFile);
                    readFile = null;
                    newFile = null;
                });
        return fileList;
    }
}
