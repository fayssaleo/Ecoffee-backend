package ECoffee.services;

import ECoffee.entities.Role;
import ECoffee.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RoleService")
public class RoleService {
    @Autowired
    private RoleRepository repo;

    public List<Role> listAll() {
        return repo.findAll();
    }

    public void save(Role role) {
        repo.save(role);
    }

    public Role get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
