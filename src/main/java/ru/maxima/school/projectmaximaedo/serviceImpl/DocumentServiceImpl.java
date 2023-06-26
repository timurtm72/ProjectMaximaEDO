package ru.maxima.school.projectmaximaedo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.maxima.school.projectmaximaedo.dto.DocumentDto;
import ru.maxima.school.projectmaximaedo.enums.DocumentType;
import ru.maxima.school.projectmaximaedo.mapper.DocumentMapper;
import ru.maxima.school.projectmaximaedo.model.*;
import ru.maxima.school.projectmaximaedo.repository.*;
import ru.maxima.school.projectmaximaedo.service.DocumentService;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;
    private final MapperUtil mapperUtil;
    private final DocumentTemplateRepository documentTemplateRepository;
    private final PartnerRepository partnerRepository;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository,
                               DocumentMapper documentMapper, MapperUtil mapperUtil,
                               DocumentTemplateRepository documentTemplateRepository,
                               PartnerRepository partnerRepository, UserRepository userRepository,
                               FileRepository fileRepository) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.mapperUtil = mapperUtil;
        this.documentTemplateRepository = documentTemplateRepository;
        this.partnerRepository = partnerRepository;
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    @Transactional
    public List<DocumentDto> getAll() {
        List<Document> documents = documentRepository.findAllByIsRemovedIsFalseOrderByIdAsc();
        if (documents == null || documents.size() == 0) {
            return null;
        }
        return mapperUtil.mapList(documents, DocumentDto.class);
    }

    @Override
    @Transactional
    public Boolean exists(Long id) {
        return documentRepository.existsByIdAndIsRemovedIsFalse(id);
    }

    @Override
    @Transactional
    public DocumentDto getById(Long id) {
        Document document = documentRepository.findDocumentByIdAndIsRemovedIsFalse(id).orElse(null);
        return document != null ? documentMapper.toDto(document) : null;
    }

    @Override
    @Transactional
    public Boolean create(DocumentDto documentDto) {
        if (documentDto == null) {
            return true;
        }
        Document document = documentMapper.toEntity(documentDto);
        if (documentDto.getDocTemplateId() != null) {
            DocumentTemplate documentTemplate = documentTemplateRepository.findDocumentTemplateByIdAndIsRemovedIsFalse(documentDto.getDocTemplateId()).orElse(null);
            if (documentTemplate != null) {
                document.setTemplate(documentTemplate);

                if (document.getCompletedFields() == null) {
                    document.setCompletedFields(new ArrayList<>());
                }
                if (documentTemplate.getTemplateFields() == null || documentTemplate.getTemplateFields().size() == 0) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Список полей шаблона пуст");
                }
                List<DocumentField> documentFields = documentTemplate.getTemplateFields()
                        .stream()
                        .filter(df -> df.getName().isEmpty())
                        .toList();
                if (documentFields.size() > 0) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Заполните все поля документа");
                } else {
                    document.setCompletedFields(new ArrayList<>(documentTemplate.getTemplateFields()));
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Нет такого шаблона для этого документа");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Выберите ID шаблона для этого документа");
        }

        if (documentDto.getPartId() != null) {
            Partner partner = partnerRepository.findPartnerByIdAndIsRemovedIsFalse(documentDto.getPartId()).orElse(null);
            if (partner != null) {
                document.setPartner(partner);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Документ с ID = " + documentDto.getDocTemplateId() + "  шаблона не найден");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Выберите ID контрагента для этого документа");
        }
        if (documentDto.getUsrId() != null) {
            User user = userRepository.findUserByIdAndIsRemovedIsFalse(documentDto.getUsrId()).orElse(null);
            if (user != null) {
                document.setUser(user);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Документ с ID = " + documentDto.getUsrId() + " пользователя не найден");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Выберите ID пользоваткля для этого документа");
        }
        if (documentDto.getFilesNumbers() != null) {
            if (document.getFiles() == null) {
                document.setFiles(new ArrayList<>());
            }
            document.getFiles().clear();
            List<AttachedFile> files = fileRepository.findAllByIsRemovedIsFalseOrderByIdAsc();
            if (files == null || files.size() == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Список файлов пуст");
            }
            for (int i = 0; i < documentDto.getFilesNumbers().size(); i++) {
                if (documentDto.getFilesNumbers().get(i) <= 0) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Индекс списка файлов не может быть меньше 0");
                }
                AttachedFile attachedFile = files.get(documentDto.getFilesNumbers().get(i) - 1);
                document.getFiles().add(attachedFile);
            }

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Выберите файлы для этого документа");
        }
        document.setCreatedAt(LocalDateTime.now());
        document.setRemoved(false);
        DocumentType documentType = documentDto.getDocumentType();
        List<Document> documents =
                documentRepository.findAllByDocumentTypeOrderByRegistryNumberAsc(documentType);
        if (documents == null || documents.size() == 0) {
            document.setRegistryNumber(1L);
        } else {
            Document documentRead = documents.get(documents.size() - 1);
            document.setRegistryNumber(documentRead.getRegistryNumber() + 1L);
        }
        documentRepository.save(document);
        return false;
    }

    @Override
    @Transactional
    public Boolean update(DocumentDto documentDto, Long id) {
        if (documentDto == null) {
            return true;
        }
        documentDto.setId(id);
        Document document = documentMapper.toEntity(documentDto);
        Document readDocument = documentRepository.findDocumentByIdAndIsRemovedIsFalse(id).orElse(null);
        if (readDocument != null) {
            document.setCreatedAt(readDocument.getCreatedAt());
            document.setRemoved(readDocument.getRemoved());
            document.setRegistryNumber(readDocument.getRegistryNumber());
            document.setUser(readDocument.getUser());
            document.setCompletedFields(readDocument.getCompletedFields());
            document.setPartner(readDocument.getPartner());
            document.setTemplate(readDocument.getTemplate());
            document.setFiles(readDocument.getFiles());
            documentRepository.save(document);
            return false;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "В базе нет документа с таким ID");
        }
    }

    @Override
    @Transactional
    public Boolean safeDelete(Long id) {
        Document document = documentRepository.findDocumentByIdAndIsRemovedIsFalse(id).orElse(null);
        if (document != null) {
            document.setRemoved(true);
            documentRepository.save(document);
            return false;
        }
        return true;
    }
}

