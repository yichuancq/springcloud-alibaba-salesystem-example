package com.example.oauth.service.permission;


import com.example.oauth.domain.SysPermission;
import com.example.oauth.repository.permission.SysPermissionRepository;
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

/**
 *
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    /**
     * @param sysPermission
     */
    @Override
    public void saveSysPermission(SysPermission sysPermission) {
        sysPermissionRepository.save(sysPermission);
    }

    @Override
    public void saveSysPermissions(List<SysPermission> sysPermissions) {
        sysPermissionRepository.saveAll(sysPermissions);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SysPermission findById(Long id) {
        SysPermission sysPermission = null;
        Optional<SysPermission> permission = sysPermissionRepository.findById(id);
        if (permission.isPresent()) {
            sysPermission = permission.get();
        }
        return sysPermission;
    }

    @Override
    public SysPermission findSysPermissionByPermission(String permission) {
        assert (!StringUtils.isEmpty(permission));
        return sysPermissionRepository.findByPermission(permission);
    }

    /**
     * 查询多个Id
     *
     * @param ids
     * @return
     */
    @Override
    public List<SysPermission> findByIds(List<Long> ids) {
        List<SysPermission> sysPermissionList = null;
        sysPermissionList = sysPermissionRepository.findAllById(ids);
        return sysPermissionList;
    }

    /**
     * @param sysPermission
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Page<SysPermission> findAllByPage(SysPermission sysPermission,
                                             int pageNumber, int pageSize) {
        //order
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(order));
        Specification<SysPermission> specification = (Specification<SysPermission>) (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<Predicate>();
            //姓名
            if (sysPermission != null && !StringUtils.isEmpty(sysPermission.getPermission())) {
                Predicate predicate = cb.equal(root.get("permission"), sysPermission.getPermission());
                predicateList.add(predicate);
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
        Page<SysPermission> page = sysPermissionRepository.findAll(specification, pageable);
        return page;
    }

    @Override
    public void deletePermissionById(Long permissionId) {
        sysPermissionRepository.deleteById(permissionId);
    }
}
