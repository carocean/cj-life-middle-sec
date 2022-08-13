package cj.life.middle.sec.service.impl;

import cj.life.middle.sec.domain.GlobalProxyLabel;
import cj.life.middle.sec.repository.GlobalProxyLabelRepository;
import cj.life.middle.sec.service.IGlobalProxyLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GlobalProxyLabelService implements IGlobalProxyLabelService {
    @Autowired
    GlobalProxyLabelRepository globalProxyLabelRepository;

    @Override
    public String createProxyLabel(String name, String iconUrl, String proxyHost, String note) {
        GlobalProxyLabel label = new GlobalProxyLabel();
        label.setName(name);
        label.setIconUrl(iconUrl);
        label.setNote(note);
        label.setProxyHost(proxyHost);
        label = globalProxyLabelRepository.save(label);
        return label.getId();
    }

    @Override
    public void removeProxyLabel(String id) {
        globalProxyLabelRepository.deleteById(id);
    }

    @Override
    public List<GlobalProxyLabel> listAllProxyLabel() {
        Iterable<GlobalProxyLabel> iterable = globalProxyLabelRepository.findAll();
        List<GlobalProxyLabel> labels = new ArrayList<>();
        iterable.forEach((globalProxyLabel -> {
            labels.add(globalProxyLabel);
        }));
        return labels;
    }

    @Override
    public List<GlobalProxyLabel> listProxyLabel(int limit, int offset) {
        Iterable<GlobalProxyLabel> iterable = globalProxyLabelRepository.findAll(PageRequest.of(offset, limit));
        List<GlobalProxyLabel> labels = new ArrayList<>();
        iterable.forEach((globalProxyLabel -> {
            labels.add(globalProxyLabel);
        }));
        return labels;
    }

    @Override
    public List<GlobalProxyLabel> listProxyLabelByName(String name, int limit, int offset) {
        Iterable<GlobalProxyLabel> iterable = globalProxyLabelRepository.findByNameLike(name, PageRequest.of(offset, limit));
        List<GlobalProxyLabel> labels = new ArrayList<>();
        iterable.forEach((globalProxyLabel -> {
            labels.add(globalProxyLabel);
        }));
        return labels;
    }

    @Override
    public List<GlobalProxyLabel> listProxyLabelByNote(String note, int limit, int offset) {
        Iterable<GlobalProxyLabel> iterable = globalProxyLabelRepository.searchByNoteLike(note, PageRequest.of(offset, limit));
        List<GlobalProxyLabel> labels = new ArrayList<>();
        iterable.forEach((globalProxyLabel -> {
            labels.add(globalProxyLabel);
        }));
        return labels;
    }

    @Override
    public GlobalProxyLabel getProxyLabel(String id) {
        Optional<GlobalProxyLabel> label = globalProxyLabelRepository.findById(id);
        return label.get();
    }
}
