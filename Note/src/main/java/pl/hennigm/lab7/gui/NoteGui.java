package pl.hennigm.lab7.gui;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hennigm.lab7.service.NoteRepo;
import pl.hennigm.lab7.service.NoteService;
import pl.hennigm.lab7.model.Note;


@Route("notes")
public class NoteGui extends VerticalLayout {
    private NoteRepo noteRepo;
    @Autowired
    public NoteGui(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
        TextArea textArea = new TextArea("Miejsce na twoją notatkę");
        textArea.getStyle().set("minWidth", "1000px");
        textArea.getStyle().set("minHeight", "500px");
        add(textArea);
        Button button1 = new Button("Zapisz");
      Button button4 = new Button("Przejdź do strony głównej");
      button4.addClickListener(event -> {
            Link link = new Link("Projektowanie_serwisow_WWW/index.html");
            link.getHref();
        });
        button1.addClickListener(event -> {
           NoteService notatka = new NoteService(noteRepo);
            notatka.saveNote(textArea.getValue());
        });
        add(button1);
        Button button2 = new Button("Wyswietl notatki");
        Button button3 = new Button("Aktualizuj wpis");
        button2.addClickListener(event -> {
            Grid<Note> grid = new Grid<>(Note.class);
            NoteService note = new NoteService(noteRepo);
            grid.setItems(note.findAllNote());
            grid.addItemClickListener(element ->{
                remove(grid);
                remove(button3);
                textArea.setValue(element.getItem().getText());
                add(button3);
                button3.addClickListener(event2 ->{
                    note.updateNote(element.getItem(),textArea.getValue());
                    remove(button3);
                });
                button1.setText("Dodaj jako nowe");
            });
            add(grid);
        });
        add(button2);
        add(button4);

    }

    }

