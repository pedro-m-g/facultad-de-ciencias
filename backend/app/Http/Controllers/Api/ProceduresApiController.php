<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Procedure;

class ProceduresApiController extends Controller
{

    public function getProcedures()
    {
        $this->authorize('viewAny', Procedure::class);
        return response()->json([
            'data' => Procedure::paginate(),
            'status' => 'success'
        ]);
    }

    public function getProcedure(int $id)
    {
        $this->authorize('view', Procedure::class);
        return Procedure::findOrFail($id);
    }
}
