package com.example.application.views.details;


import com.example.application.cache.Cache;
import com.example.application.models.LoadedItem;
import com.example.application.views.MainLayout;
import com.example.application.views.shared.SharedViews;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "detail-view", layout = MainLayout.class)
@PageTitle("Movie Detail")
@CssImport("./views/detail.css")
public class DetailView extends Div {

    private Button favoriteAction = new Button();
    private Button goBack = new Button();


    public DetailView() {

        addClassName("detail-wrap");
        add(SharedViews.getDetail(Cache.getInstance().getDetailItem(), Cache.getInstance().isFavMode()));
        add(createButtonLayout());

        favoriteAction.setText("Watch the trailer");
        favoriteAction.setClassName("green-button");
        favoriteAction.addClickListener(e -> favoriteAction.getUI().ifPresent(ui -> {
                        openPreview(Cache.getInstance().getDetailItem());
                }
        ));

        goBack.setText("Return to Movie Search");
        goBack.addClickListener(
                e -> goBack.getUI().ifPresent(ui -> { ui.navigate("movie-search"); }
                ));
    }

    public void openPreview(LoadedItem item) {
        if (item.getPreview() != null)
            getUI().get().getPage().open(item.getPreview());
    }



    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        favoriteAction.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(favoriteAction);
        buttonLayout.add(goBack);
        return buttonLayout;
    }


}

