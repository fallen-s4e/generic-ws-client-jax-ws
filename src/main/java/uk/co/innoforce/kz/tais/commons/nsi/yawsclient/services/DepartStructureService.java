package uk.co.innoforce.kz.tais.commons.nsi.yawsclient.services;

import uk.co.innoforce.kz.tais.commons.nsi.yawsclient.IEnvironment;
import uk.co.innoforce.kz.tais.commons.nsi.yawsclient.generated.DepartStructure;
import uk.co.innoforce.kz.tais.commons.nsi.yawsclient.generated.DepartStructureService_Service;
import uk.co.innoforce.kz.tais.commons.nsi.yawsclient.generated.ListResult;

/**
 * @author magzhan.karasayev
 * @since 21.05.14 19:03
 */
public class DepartStructureService extends AbstractService<
        uk.co.innoforce.kz.tais.commons.nsi.yawsclient.generated.DepartStructureService, DepartStructure, String> {

    public DepartStructureService(IEnvironment env) {
        super(new DepartStructureService_Service().getDepartStructureServicePort(), DepartStructure.class,
                env.getUrlPart() + "DepartStructureService");
    }

    @Override
    protected ListResult getListResult() throws Exception {
        return getPort().findAllDepartStructure("RU");
    }

    @Override
    protected String getId(DepartStructure item) {
        return item.getCode();
    }
}
