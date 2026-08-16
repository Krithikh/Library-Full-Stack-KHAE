package self.learning.backend.lib.mgmt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import self.learning.backend.lib.mgmt.dao.DepartmentDao;

@SpringBootTest
class BaselineApplicationSmokeTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    void baselineStartsAndReadsPresenterSeedData() {
        var department = departmentDao.findById(1L);

        assertTrue(department.isPresent());
        assertEquals("CSE", department.get().getDepartmentCode());
        assertEquals("Computer Science and Engineering", department.get().getDepartmentName());
    }
}
