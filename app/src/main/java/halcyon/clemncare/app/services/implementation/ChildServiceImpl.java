package halcyon.clemncare.app.services.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.model.Child;
import halcyon.clemncare.app.model.Guardian;
import halcyon.clemncare.app.repositories.ChildRepository;
import halcyon.clemncare.app.services.ChildService;

@Service
public class ChildServiceImpl implements ChildService{

    @Autowired
    ChildRepository childRepository;

    @Override
    public String createChild(Child child) {
        childRepository.save(child);
        return "Child Saved Successfully";
    }

    @Override
    public String updateChild(Child child) {
        childRepository.save(child);
        return "Child Updated Successfully";
    }

    @Override
    public String deleteChild(Long childId) {
        childRepository.deleteById(childId);
        return "Child Removed Successfully";
    }

    @Override
    public Child getChild(Long childId) {
        return childRepository.findById(childId).get();
    }

    @Override
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    @Override
    public Set<Guardian> findGuardiansByChildId(Long childId) {
        return childRepository.findGuardiansByChildId(childId);
    }
    
}