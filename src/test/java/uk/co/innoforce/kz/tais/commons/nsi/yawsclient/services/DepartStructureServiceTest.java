package uk.co.innoforce.kz.tais.commons.nsi.yawsclient.services;

import junit.framework.Assert;
import org.junit.Test;
import uk.co.innoforce.kz.tais.commons.nsi.yawsclient.Environment;
import uk.co.innoforce.kz.tais.commons.nsi.yawsclient.IEnvironment;

/**
 * @author magzhan.karasayev
 * @since 22.05.14 12:25
 */
public class DepartStructureServiceTest {
    private IEnvironment malformedEnvironment = new IEnvironment() {
        @Override
        public String getUrlPart() {
            return "malformedUrl";
        }
    };

    @Test
    public void testGetAll() throws Exception {
        DepartStructureService service = new DepartStructureService(Environment.TEST);
        Assert.assertNotNull(service.getAllList());
        Assert.assertNotNull(service.getAllMap());
        Assert.assertNotNull(service.getAllMapCached());
    }

    @Test
    public void testGetAllWrongAddress() throws Exception {
        DepartStructureService service = new DepartStructureService(malformedEnvironment);
        Assert.assertNull(service.getAllList());
        Assert.assertNull(service.getAllMap());
        Assert.assertNull(service.getAllMapCached());
    }
}
