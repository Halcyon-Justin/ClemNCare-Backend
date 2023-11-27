package halcyon.clemncare.app.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.dto.ReportCardRequest;
import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.model.ReportCard;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.repositories.ReportCardRepository;
import halcyon.clemncare.app.service.ReportCardService;

@Service
public class ReportCardServiceImpl implements ReportCardService {

    @Autowired
    ChildRepository childRepository;

    @Autowired
    ReportCardRepository reportCardRepository;

    @Override
    @Transactional
    public ReportCard createReportCard(ReportCardRequest reportCardRequest, Long id) {
        try {

            // Retrieve the Child by ID
            Child child = childRepository.getById(id);

            Family family = child.getFamily();
            List<String> guardianEmails = family.getGuardians().stream()
                    .filter(guardian -> Boolean.FALSE.equals(guardian.getIsEmergencyContact()))
                    .map(Guardian::getEmailAddress)
                    .collect(Collectors.toList());

            // Create a new ReportCard object
            ReportCard reportCard = new ReportCard();
            reportCard.setChildId(id);
            reportCard.setHasNapped(reportCardRequest.getHasNapped());
            reportCard.setNotes(reportCardRequest.getNotes());
            reportCard.setSendTo(guardianEmails);

            // Save the ReportCard
            return reportCardRepository.save(reportCard);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions as needed
            throw new RuntimeException("Error creating report card", e);
        }
    }

    @Override
    public String updateReportCard(ReportCard reportCard) {
        reportCardRepository.save(reportCard);
        return "Report Card Updated Successfully";
    }

    @Override
    public String deleteReportCard(Long reportCardId) {
        reportCardRepository.deleteById(reportCardId);
        return "Report Card Deleted Successfully";
    }

    @Override
    public ReportCard getReportCard(Long reportCardId) {
        return reportCardRepository.findById(reportCardId).orElse(null);
    }

    @Override
    public List<ReportCard> getAllReportCardsByChildId(Long childId) {
        return reportCardRepository.findByChildId(childId);
    }

}