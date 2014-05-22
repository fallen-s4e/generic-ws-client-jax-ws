package uk.co.innoforce.kz.tais.commons.nsi.yawsclient.services;

import uk.co.innoforce.kz.tais.commons.nsi.yawsclient.generated.BaseDictionary;
import uk.co.innoforce.kz.tais.commons.nsi.yawsclient.generated.ListResult;

import javax.xml.ws.BindingProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * P is an interface that is returned by new SomeJaxWsGeneratedService().getSomeJaxWsGeneratedPort()
 * I is a class of items being returned and
 * IKey is a unique id of that item(e.g code or id).
 * internally caching implemented with TreeMap to provide sorting by getId(item) by default when using getAllMap or
 * getAllMapCached.
 *
 * @author magzhan.karasayev
 * @since 21.05.14 19:04
 */
public abstract class AbstractService<P, I, IKey> {

    private P port;
    private Class<I> itemType;
    private Map<IKey, I> cache;
    private final Logger log = Logger.getLogger(AbstractService.class.getName());

    protected abstract ListResult getListResult() throws Exception;
    protected abstract IKey getId(I item);

    /**
     *
     * @param port
     * @param itemType
     * @param endPoint
     */
    protected AbstractService(P port, Class<I> itemType, String endPoint) {
        this.port = port;
        this.itemType = itemType;
        setEndpoint(endPoint);
    }

    public P getPort() {
        return port;
    }

    /**
     * this method contains unsafe casting, didn't find better way to set new endpoint. Fix it if you know how.
     * @param newEdnpoint
     */
    private void setEndpoint(String newEdnpoint) {
        BindingProvider bindingProvider = (BindingProvider) port;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, newEdnpoint);
    }

    public List<I> getAllList() {
        try {
            List<I> res = new ArrayList<I>();
            ListResult result = getListResult();
            if(result != null && result.getEntity() != null){
                for (BaseDictionary item : result.getEntity()) {
                    res.add(itemType.cast(item));
                }
            }
            return res;
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Map<IKey, I> getAllMap() {
        List<I> list = getAllList();
        if (list != null) {
            Map<IKey, I> map = new TreeMap<IKey, I>();
            for (I i : list) {
                map.put(getId(i), i);
            }
            return map;
        }
        return null;
    }

    public Map<IKey, I> getAllMapCached() {
        if (cache == null) {
            cache = getAllMap();
        }
        return cache;
    }
}
