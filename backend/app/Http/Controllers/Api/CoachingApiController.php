<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Coach;

class CoachingApiController extends Controller
{

    public function getCoaches()
    {
        return response()->json([
            'data' => Coach::paginate(),
            'status' => 'success'
        ]);
    }

    public function getCoach(int $id)
    {
        $this->authorize('view', Coach::class);
        return Coach::findOrFail($id);
    }
}
