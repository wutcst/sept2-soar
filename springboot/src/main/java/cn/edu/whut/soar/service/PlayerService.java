package cn.edu.whut.soar.service;

import cn.edu.whut.soar.db.ItemStore;
import cn.edu.whut.soar.db.PlayerStore;
import cn.edu.whut.soar.entity.ItemEntity;
import cn.edu.whut.soar.entity.PlayerEntity;
import cn.edu.whut.soar.model.GetPlayerInfoResponse;
import cn.edu.whut.soar.model.Status;
import cn.edu.whut.soar.model.TakeItemRequest;
import cn.edu.whut.soar.model.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private static final String ownerTypePlayer = "Player";

    @Autowired
    private PlayerStore playerStore;

    @Autowired
    private ItemStore itemStore;

    public PlayerService() {
    }

    public GetPlayerInfoResponse getPlayerInfo() {
        PlayerEntity player = playerStore.getPlayerEntity();

        return new GetPlayerInfoResponse(
                player.getName(),
                player.getMaxCarryWeight(),
                player.getCurrentRoomId()
        );
    }

    public double getCurrentCarryWeight() {
        PlayerEntity player = playerStore.getPlayerEntity();

        List<ItemEntity> items = itemStore.getItemsByOwner(ownerTypePlayer, player.getId());

        double currentCarryWeight = 0;
        for (ItemEntity item : items) {
            currentCarryWeight += item.getWeight();
        }

        return currentCarryWeight;
    }

    public StatusResponse takeItem(TakeItemRequest request) {
        PlayerEntity player = playerStore.getPlayerEntity();

        ItemEntity item = itemStore.getItem(request.itemId);

        if (item.getOwnerType().equals(ownerTypePlayer)) {
            return new StatusResponse(Status.NotInCurrentRoom);
        }

        if (item.getOwnerId() != player.getCurrentRoomId()) {
            return new StatusResponse(Status.NotInCurrentRoom);
        }

        if (getCurrentCarryWeight() + item.getWeight() > player.getMaxCarryWeight()) {
            return new StatusResponse(Status.ExceedMaxCarryWeight);
        }

        item.setOwnerType(ownerTypePlayer);
        item.setOwnerId(player.getId());

        return new StatusResponse(Status.Success);
    }

    public List<ItemEntity> getItems() {
        PlayerEntity player = playerStore.getPlayerEntity();

        return itemStore.getItemsByOwner(ownerTypePlayer, player.getId());
    }
}
