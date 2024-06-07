package com.bhoruka.bloodbank.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.bhoruka.bloodbank.TestCampConstants;
import com.bhoruka.bloodbank.dao.entity.Camp;
import com.bhoruka.bloodbank.dao.repository.CampRepository;
import com.bhoruka.bloodbank.model.CampModel;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CampDaoTest {

    @Mock
    private CampRepository campRepository;

    @InjectMocks
    private CampDao campDao;

    private Camp campFromRepository;

    @BeforeEach
    public void setup() {
        campDao = new CampDao(campRepository);

        campFromRepository = new Camp();
        campFromRepository.setId(TestCampConstants.TEST_CAMP_ID);
        campFromRepository.setPartnerId(TestCampConstants.TEST_PARTNER_ID);
        campFromRepository.setDateOfCamp(TestCampConstants.TEST_DATE);
        campFromRepository.setExpectedNoOfDonor(200L);
    }

    @Test
    public void constructor_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            campDao = new CampDao(null);
        });
    }
    public void createCamp_success() {
        when(campRepository.save(any())).thenReturn(campFromRepository);

        CampModel campModel = campDao.createCamp(TestCampConstants.CAMP_MODEL_WITHOUT_ID);

        assertThat(campModel, is(TestCampConstants.VALID_CAMP_MODEL));
    }

    @Test
    public void getCamp_exists_returnsCamp() {
        when(campRepository.findById(any())).thenReturn(Optional.of(campFromRepository));

        assertThat(campDao.getCamp(TestCampConstants.TEST_CAMP_ID),
                is(Optional.of(TestCampConstants.VALID_CAMP_MODEL)));
    }

    @Test
    public void getCamp_doesNotExist_returnsEmpty() {
        when(campRepository.findById(any())).thenReturn(Optional.empty());

        assertThat(campDao.getCamp(TestCampConstants.TEST_CAMP_ID), is(Optional.empty()));
    }

    @Test
    public void campExists_validId_returnsTrue() {
        when(campRepository.existsById(any())).thenReturn(Boolean.TRUE);

        assertThat(campDao.campExists(TestCampConstants.TEST_CAMP_ID), is(Boolean.TRUE));
    }

    @Test
    public void campExists_invalidId_returnsFalse() {
        when(campRepository.existsById(any())).thenReturn(Boolean.FALSE);

        assertThat(campDao.campExists(TestCampConstants.TEST_CAMP_ID), is(Boolean.FALSE));
    }

    @Test
    public void createCamp_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            campDao.createCamp(null);
        });
    }

    @Test
    public void getCamp_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            campDao.getCamp(null);
        });
    }

    @Test
    public void campExists_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            campDao.campExists(null);
        });
    }
}
