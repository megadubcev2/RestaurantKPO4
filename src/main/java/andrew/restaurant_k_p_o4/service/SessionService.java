package andrew.restaurant_k_p_o4.service;

import andrew.restaurant_k_p_o4.domain.Session;
import andrew.restaurant_k_p_o4.model.SessionDTO;
import andrew.restaurant_k_p_o4.repos.SessionRepository;
import andrew.restaurant_k_p_o4.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(final SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<SessionDTO> findAll() {
        final List<Session> sessions = sessionRepository.findAll(Sort.by("id"));
        return sessions.stream()
                .map((session) -> mapToDTO(session, new SessionDTO()))
                .toList();
    }

    public SessionDTO get(final Integer id) {
        return sessionRepository.findById(id)
                .map((session) -> mapToDTO(session, new SessionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final SessionDTO sessionDTO) {
        final Session session = new Session();
        mapToEntity(sessionDTO, session);
        return sessionRepository.save(session).getId();
    }

    public void update(final Integer id, final SessionDTO sessionDTO) {
        final Session session = sessionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(sessionDTO, session);
        sessionRepository.save(session);
    }

    public void delete(final Integer id) {
        sessionRepository.deleteById(id);
    }

    private SessionDTO mapToDTO(final Session session, final SessionDTO sessionDTO) {
        sessionDTO.setId(session.getId());
        sessionDTO.setUserId(session.getUserId());
        sessionDTO.setSessionToken(session.getSessionToken());
        sessionDTO.setExpiresAt(session.getExpiresAt());
        return sessionDTO;
    }

    private Session mapToEntity(final SessionDTO sessionDTO, final Session session) {
        session.setUserId(sessionDTO.getUserId());
        session.setSessionToken(sessionDTO.getSessionToken());
        session.setExpiresAt(sessionDTO.getExpiresAt());
        return session;
    }

}
