package com.example.oauth.service.role;

import com.example.oauth.domain.SysRole;
import com.example.oauth.repository.role.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleRepository sysRoleRepository;

    /**
     * @param sysRole
     */
    @Override
    public void saveRole(SysRole sysRole) {
        assert (sysRole != null);
        sysRoleRepository.save(sysRole);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SysRole findRoleById(Long id) {
        SysRole sysRole = null;
        Optional<SysRole> role = sysRoleRepository.findById(id);
        if (role.isPresent()) {
            sysRole = role.get();
        }
        return sysRole;
    }

    @Override
    public SysRole findRoleByRoleName(String roleName) {
        assert (roleName != null);
        return sysRoleRepository.findByRole(roleName);
    }

    @Override
    public void deleteRoleById(Long id) {
        sysRoleRepository.deleteById(id);
    }

    @Override
    public Page<SysRole> findAllByPage(SysRole sysRole, int pageNumber, int pageSize) {

        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(order));
        Specification<SysRole> specification = (Specification<SysRole>) (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<Predicate>();
            //姓名
            if (sysRole != null && !StringUtils.isEmpty(sysRole.getRole())) {
                Predicate predicate = cb.equal(root.get("role"), sysRole.getRole());
                predicateList.add(predicate);
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
        Page<SysRole> page = sysRoleRepository.findAll(specification, pageable);
        return page;
    }

}
