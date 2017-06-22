package cn.springlogic.message.domain.web;

import cn.springlogic.message.domain.jpa.entity.MessagePublicationComment;
import cn.springlogic.message.domain.jpa.entity.MessagePublicationFavor;
import cn.springlogic.message.domain.jpa.repository.MessagePublicationFavorRepository;
import cn.springlogic.social.jpa.entity.Publication;
import cn.springlogic.social.jpa.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/6/14.
 */
@RepositoryRestController
@RequestMapping(value = "message:message-publication-favor")
public class MessagePublicationFavorController {
    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;
    @Autowired
    private MessagePublicationFavorRepository messagePublicationFavorRepository;
    @Autowired
    private PublicationRepository publicationRepository;




    @ResponseBody
    @GetMapping(value = "/search/custom-user")
    public ResponseEntity<PagedResources<PersistentEntityResource>> customMessagePublicationCommentPage(@RequestParam("userId") Integer userId,
                                                                                                        Pageable pageable,
                                                                                                        PersistentEntityResourceAssembler resourceAssembler) {

        Page<MessagePublicationFavor> messagePublicationFavorPage = messagePublicationFavorRepository.findByUser(userId, pageable);

        Page<MessagePublicationFavor> customMessagePublicationFavorPage = messagePublicationFavorPage.map(new MessagePublicationFavorController.CustomPublicationFavorConverter(publicationRepository));

        return ResponseEntity.ok(pagedResourcesAssembler.toResource(customMessagePublicationFavorPage, resourceAssembler));
    }

    /**
     *转换器类
     */
    public static final class CustomPublicationFavorConverter implements Converter<MessagePublicationFavor, MessagePublicationFavor> {

        private PublicationRepository publicationRepository;

        public CustomPublicationFavorConverter(PublicationRepository publicationRepository) {
            this.publicationRepository = publicationRepository;
        }

        @Override
        public MessagePublicationFavor convert(MessagePublicationFavor source) {

            Publication one = publicationRepository.findOne(source.getPublicationId());
            if(one!=null){
                source.setPublication(one);
            }

            return source;
        }
    }
}


